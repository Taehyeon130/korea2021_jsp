<%@page import="site0617.model.domain.Gallery"%>
<%@page import="site0617.model.gallery.dao.GalleryDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!GalleryDAO galleryDAO = new GalleryDAO();%>
<%
	//리스트페이지에서 전송된 파라미터 받기 
	String gallery_id=request.getParameter("gallery_id");
	Gallery gallery=galleryDAO.select(Integer.parseInt(gallery_id));//레코드 1건 가져오기!!
%>    
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}
input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}
input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
input[type=button]:hover {
  background-color: #45a049;
}
.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/4.16.1/standard/ckeditor.js"></script>
<script type="text/javascript">
$(function(){
	CKEDITOR.replace("content");
	
	var btn=$("input[type='button']"); //버튼을 배열로 받음
	
	//버튼에 이벤트 연결하기 
	$(btn[0]).click(function(){
		edit();	
	});
	
	$(btn[1]).click(function(){
		del();	
	});
	
	$(btn[2]).click(function(){
		location.href="/gallery/list.jsp";	
	});
});

function edit(){
	if(confirm("수정하시겠어요?")){
		$("form").attr({
			"action":"/gallery/edit.jsp", //일부러 jsp 
			"enctype":"multipart/form-data", //바이너리 파일 넘길때에는 반드시 추가해줘야함
			"method":"post"
		});	
		$("form").submit();		
	}
}

function del(){
	if(confirm("삭제하시겠어요?")){
		$("form").attr({
			"action":"/delete",
			"method":"post"
		});	
		$("form").submit();		
	}
}
//주의!!! 클라이언트가 서버에 문자열이 아닌 파일자체를 전송하려면 반드시 
//form태그에 multipart/form-data가 명시되어야 한다!!
//이때, 서버에서는 기존의 request 객체가 보유한 getParameter() 메서드로는
//바이너리 파일이 포함된 요청을 처리할 수 없다!!
function regist(){
	$("form").attr({
		//"action":"/gallery/upload.jsp",
		"action":"/upload",
		"enctype":"multipart/form-data",
		"method":"post"
	});	
	$("form").submit();
}
</script>
</head>
<body>

<h3>파일업로드 양식</h3>

<div class="container">
  <form>
  	<!-- 아래의 히든은 수정, 삭제 모두 공통이므로.... -->
  	<input type="hidden" name="gallery_id" value="<%=gallery_id%>">
  	<input type="hidden" name="filename" value="<%=gallery.getFilename()%>">
  	
    <input type="text" 	name="title" 			value="<%=gallery.getTitle()%>">
    <input type="text" 	name="writer" 		value="<%=gallery.getWriter()%>">
    <textarea name="content" 	style="height:200px"><%=gallery.getContent()%></textarea>
    <input type="file" name="myfile">
    <p>
   	<img src="/data/<%=gallery.getFilename()%>" width ="200px"> 
    <p>
    <input type="button" value="수정">
    <input type="button" value="삭제">
    <input type="button" value="목록">
  </form>
</div>

</body>
</html>
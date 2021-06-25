<%@page import="com.koreait.site0622.model.domain.News"%>
<%@page import="com.koreait.site0622.model.news.dao.MybatisNewsDAO"%>
<%@page import="com.koreait.site0622.model.news.dao.NewsDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!NewsDAO newsDAO = new MybatisNewsDAO(); %>
<%
	int news_id = Integer.parseInt(request.getParameter("news_id"));
	News news = newsDAO.select(news_id);
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
<script src="https://unpkg.com/react@16/umd/react.production.min.js"></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js"></script>
<script src="https://unpkg.com/babel-standalone@6.15.0/babel.min.js"></script>

<script src="/js/Comments.js"></script>
<script type="text/babel">
	class CustomComments extends React.Component{
		render(){
			//return 영역 밖에서 원하는 태그를 구성한 후, 완성된 태그를 return안에서 사용하면 된다..
			var tag=[];
			for(var i=0;i<this.props.result.commentsList.length;i++){
				var obj = this.props.result.commentsList[i];
				tag.push(
				<div>
					<input type="text" value={obj.msg}  style={{width:"60%"}}/>
					<input type="text" value={obj.cwriter} style={{width:"20%"}}/>
					<input type="text" value={obj.cdate} style={{width:"15%"}}/>
				</div>
				);
			}
			return 	<div>						
							{tag}
						</div>
		}
	}

$(function(){
	
	CKEDITOR.replace("content");
	
	var bt_list=$("input[type='button']")[0]; //목록
	var bt_edit=$("input[type='button']")[1];//수정
	var bt_del=$("input[type='button']")[2];//삭제
	
	//버튼에 이벤트 연결하기 
	$(bt_list).click(function(){
		location.href="/news/list.jsp";	
	});
	$(bt_edit).click(function(){
		//regist();	
	});
	$(bt_del).click(function(){
		//regist();	
	});
	
	//방법3) React의 컴포넌트를 이용하는 법 
	getCommentsList();//상세보기가 로드되면, 댓글 리스트 가져오기
});
//수정요청
function edit(){
	$("#form1").attr({
		"action":"",
		"method":"post"
	});	
	$("#form1").submit();
}
//댓글 등록(새로고침 없이!! 즉 비동기로 처리)
function registComments(){
	var formdata = $("#form2").serialize(); //폼양식을 전송할 수 있는 문자열로 변환
	
	$.ajax({
		url:"/comments/regist",
		type:"post", 
		data:formdata,//폼을 전송할 수 있는 데이터화 시킨후, 자체를 전부 전송
		success:function(result, status, xhr){
			if(result==1){
				//댓글 목록가져와서 동적으로 늘려나가기
				getCommentsList();//현재 뉴스기사에 딸린, 댓글 가져오기 
			}
		}
	});
}
function getCommentsList(){
	//비동기방식으로 댓글 리스트 요청하자!!!
	$.ajax({
		url:"/comments/list?news_id=<%=news.getNews_id()%>",
		type:"get",
		success:function(result, status, xhr){
			//넘겨받은 데이터가 string일 경우 json으로 파싱하자
			//var json = JSON.parse(result);
			//console.log(json.commentsList.length);
			
			//넘겨받은 데이터가 json 자체일 경우는 파싱할 필요없다
			console.log(result);	
			ReactDOM.render(<CustomComments result={result}/> , document.getElementById("commentsArea"))
		}
	});
}
//댓글 목록 출력하기 (방법1: 출력대상 컨텐츠를 문자열로 처리하는 방법 유지보수가 까다롭다.)
function printCommentsList(json){ //js는 자료형이 존재하지 않음 (자바랑 틀림)
	$("#commentsArea").html("");//기존의 commentsArea의 컨텐츠를 초기화!!(div에서 제거 )
	
	var tag="";
	
	for(var i=0;i<json.commentsList.length;i++){
		var comments = json.commentsList[i]; //댓글 하나 꺼내기
		tag+="<div>";
		tag+="<input type=\"text\" value=\""+comments.msg+"\" style=\"width:60%\" readonly>";
		tag+="<input type=\"text\" value=\""+comments.cwriter+"\" style=\"width:20%\" readonly>";
		tag+="<input type=\"text\" value=\""+comments.cdate+"\" style=\"width:10%\" readonly>";
		tag+="</div>";
	}
	/*
	자바스크립트로 구현
	var commentsArea=document.getElementById("commentsArea");
	commentsArea.innerHTML=tag;
	*/
	
	//jquery 로 구현
	$("#commentsArea").append(tag);
}
//방법2)  출력대상이 되는 태그를 객체로 처리하는 법 
function printCommentsList2(json){
	$("#commentsArea").html("");//기존에 데이터 삭제 
	
	for(var i=0;i<json.commentsList.length;i++){
		var obj=json.commentsList[i];
		var comments=new Comments(document.getElementById("commentsArea"),obj.msg, obj.cwriter, obj.cdate);
	}	
}
</script>
<script>
</script>
</head>
<body>

<h3>상세보기</h3>

<div class="container">
  <form id="form1">
    <input type="hidden" 	name="news_id" 	value="<%=news.getNews_id()%>">
    <input type="text" 	name="title" 			value="<%=news.getTitle()%>">
    <input type="text" 	name="writer" 		value="<%=news.getWriter()%>">
    <textarea 				name="content" 	style="height:200px"><%=news.getContent()%></textarea>
    <input type="button" value="목록">
    <input type="button" value="수정">
    <input type="button" value="삭제">
  </form>
</div>
<div>
	<form id="form2">
		<input type="hidden" 	name="news_id" 	value="<%=news.getNews_id()%>">
		<input type="text" name="msg" 		placeholder="댓글 메시지..." style="width:60%">
		<input type="text" name="cwriter" 	placeholder="작성자..." style="width:20%">
		<input type="button" value="댓글등록"  style="width:10%" onClick="registComments()">
	</form>
</div>
<div id="commentsArea"> 
	
</div>

</body>
</html>
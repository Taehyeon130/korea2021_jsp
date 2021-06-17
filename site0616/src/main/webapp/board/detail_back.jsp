<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<% 
	Class.forName("oracle.jdbc.driver.OracleDriver");

	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","webmaster","1234");
	
	//list.jsp의 링크를 통해 전송되어온 파라미터 값 받기!!!
	String board_id = request.getParameter("board_id");
	String sql= "select *from board where board_id ="+board_id;

	pstmt = con.prepareStatement(sql);
	rs = pstmt.executeQuery();
	rs.next(); //커서 한칸 이동
	out.print(sql);
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
	
	$("#bt_edit").click(function(){
		if(confirm("수정하시겠어요?")){
			edit();			
		}
	});
	$("#bt_del").click(function(){
		if(confirm("삭제하시겠어요?")){
			del();			
		}
	});
	$("#bt_list").click(function(){
		location.href="/board/list.jsp";
	});
});

function edit(){
	$("form").attr({
		"action":"/board/edit.jsp",
		"method":"post"
	});	
	$("form").submit();
}

function del(){
	$("form").attr({
		"action":"/board/del.jsp",
		"method":"post"
	});	
	$("form").submit();
}

</script>
</head>
<body>

<h3>상세페이지</h3>

<div class="container">
  <form>
  	<input type="hidden" name="board_id" value="<%=rs.getString("board_id") %>">
    <input type="text" 	name="title" 		 value="<%=rs.getString("title") %>">
    <input type="text" 	name="writer" 		value="<%=rs.getString("writer") %>">
    <textarea 					name="content"  style="height:200px"><%=rs.getString("content") %></textarea>
    <input type="button" value="수정" id="bt_edit">
    <input type="button" value="삭제"id="bt_del">
    <input type="button" value="목록" id="bt_list">
  </form>
</div>

</body>
</html>
<%
	if(con!=null)con.close();
	if(pstmt!=null)pstmt.close();
	if(rs!=null)rs.close();
%>
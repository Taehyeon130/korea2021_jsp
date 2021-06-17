<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","webmaster","1234");
	if(con==null){
		out.print("접속실패<br>");
	}else{
		String sql="select * from board order by board_id desc";
		pstmt=con.prepareStatement(sql);
		rs=pstmt.executeQuery();//쿼리수행 및 결과집합 가져오기 
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
  position:relative;
  left:-1000px;
}
th, td {
  text-align: left;
  padding: 16px;
}
tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
    var table = $("table");  
    table.animate({left: '0px'}, "slow");	
});
</script>
</head>
<body>

<h2>게시판 목록</h2>

<table>
	<tr>
		<th>No</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일</th>
		<th>조회수</th>
	</tr>
	<%while(rs.next()){ %>
	<tr>
		<td>Jill</td>
		<td><a href="/board/detail.jsp?board_id=<%=rs.getInt("board_id")%>"><%=rs.getString("title") %></a></td>
		<td><%=rs.getString("writer") %></td>
		<td><%=rs.getString("regdate") %></td>
		<td><%=rs.getInt("hit") %></td>
	</tr>
	<%} %>
	<tr>
		<td colspan="5">
			<button onClick="location.href='/board/regist.jsp';">등록</button>
		</td>
	</tr>
</table>
</body>
</html>
<%
	if(con!=null)con.close();
	if(pstmt!=null)pstmt.close();
	if(rs!=null)rs.close();
%>
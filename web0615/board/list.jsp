<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%!
	//선언부 - 멤버영역에 해당
	Connection con =null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
%>

<%
	//스크립틀릿 - 메서드 영역
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","webmaster","1234");
	if(con==null){
		out.print("접속 실패<br>");
	}else{
		out.print("접속 성공<br>");

		String sql = "select *from board order by board_id desc";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
	}
%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>Document</title>
 </head>
 <body>
<table width="100%" border="1px">
	<tr>
		<th>No</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<%while(rs.next()){%>
	<tr>
		<td>No</td>
		<td><%=rs.getString("title")%></td>
		<td><%=rs.getString("writer")%></td>
		<td><%=rs.getString("regdate")%></td>
		<td><%=rs.getInt("hit")%></td>
	</tr>
	<%}%>
</table>
 </body>
</html>
<%
	rs.close();
	pstmt.close();
	con.close();
%>
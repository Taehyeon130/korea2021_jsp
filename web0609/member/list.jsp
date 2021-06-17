<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.PreparedStatement"%>
<%@ page import = "java.sql.ResultSet"%>
<%!
	//선언부 : 클래스의 멤버영역으로 변수 및 메서드를 선언할 수 있는 영역
	String driver="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/javase?characterEncoding=UTF-8";
	String user="root";
	String password="1234";

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

%>
<%
	//스크립틀릿 영역 : 클래스의 메서드 영역, 따라서 로직 작성 가능

	Class.forName(driver);//1. 드라이버 로드
	out.print("드라이버 로드 성공"); //드라이버를 lib공용 jar 디렉토리에 넣어뒀기 때문에...

	//2. 접속
	con = DriverManager.getConnection(url,user,password);
	if(con==null){
		out.print("접속 실패<br>");
	}else{
		out.print("접속 성공<br>");

		//3. 쿼리수행
		String sql = "select *from member";
		pstmt = con.prepareStatement(sql); //쿼리준비
		rs = pstmt.executeQuery(); //쿼리 실행 및 결과 가져오기
		out.print(rs);
		rs.close();
		pstmt.close();
		con.close();


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
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}
</style>
</head>
<body>

<h2>Zebra Striped Table</h2>
<p>For zebra-striped tables, use the nth-child() selector and add a background-color to all even (or odd) table rows:</p>

<table>
  <tr>
    <th>No</th>
    <th>member_id</th>
    <th>user_id</th>
	<th>name</th>
	<th>regdate</th>
  </tr>
  <tr>
    <td>Jill</td>
    <td>Smith</td>
    <td>50</td>
	<td>50</td>
	<td>50</td>
  </tr>
</table>

</body>
</html>

<%@ page contentType = "text/html;charset=utf-8"%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <title>게시판 목록</title>
 </head>
 <body>
	<table width="100%" border="1px">
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%for(int i=1;i<=10;i++){%>
		<tr>
		<td>1</td>				
		<td>안녕</td>
		<td>홍길동</td>
		<td>2021-06-14</td>			
		<td>23</td>
		</tr>
		<%}%>
	</table>
 </body>
</html>

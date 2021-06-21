<%@page import="site0617.model.domain.Gallery"%>
<%@page import="java.util.List"%>
<%@page import="site0617.model.gallery.dao.GalleryDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!GalleryDAO galleryDAO = new GalleryDAO();%>
<%
	List<Gallery> galleryList=galleryDAO.selectAll(); //게시물 가져오기
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
		<th>이미지</th>
		<th>제목</th>
		<th>작성자</th>
		<th>등록일</th>
		<th>조회수</th>
	</tr>
	<%for(Gallery gallery:galleryList){ %>
	<tr>
		<td>Jill</td>
		<td><img src="/data/<%=gallery.getFilename()%>" width="50px"></td>
		<td>
			<a href="/gallery/detail.jsp?gallery_id=<%=gallery.getGallery_id()%>"><%=gallery.getTitle() %></a>
		</td>
		<td><%=gallery.getWriter() %></td>
		<td><%=gallery.getRegdate() %></td>
		<td><%=gallery.getHit() %></td>
	</tr>
	<%} %>
	<tr>
		<td colspan="6">
			<button onClick="location.href='/photo/regist.jsp';">등록</button>
		</td>
	</tr>
</table>
</body>
</html>
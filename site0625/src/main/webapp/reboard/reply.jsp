<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//답변등록에 필요한 파라미터인 team,step,depth를 받자!!
	String team = request.getParameter("team");
	String step= request.getParameter("step");
	String depth= request.getParameter("depth");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
#box{border:1px solid #CCCCCC}
#writer,#title,#content{font-size:9pt;font-weight:bold;color:#7F7F7F;돋움}
input{
	font-size:9pt;
	border-left:1px solid #C3C3C3;
	border-top:1px solid #C3C3C3;
	border-right:1px solid #C3C3C3;
	border-bottom:1px solid #C3C3C3;
	color:#7F7F7F;돋움
}
#title input{width:250px;}
#content textarea{
width:503px;
border:0;
height:153;
background:url("/reboard/images/write_bg.gif");
border:#C3C3C3 1px solid 
}
#copyright{font-size:9pt;}
a{text-decoration:none}
img{border:0px}
</style>
<%@include file="/reboard/inc/lib.jsp" %>
<script type="text/javascript">
//답변 등록 요청하기 
function reply(){
	//동기방식으로 요청할 것임
	$("form").attr({
		"action":"/reboard/reply",
		"method":"post"
	});
	$("form").submit();
}
</script>
</head>
<body>
답변 등록 폼
	<form>
	<input type="hidden" name = "team" value="<%=team%>">
	<input type="hidden" name = "step" value="<%=step%>">
	<input type="hidden" name = "depth" value="<%=depth%>">
	
		<table id="box" align="center" width="603" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td><img src="/reboard/images/ceil.gif" width="603" height="25"></td>
			</tr>
			<tr>
				<td height="2" bgcolor="#6395FA"><img
					src="/reboard/images/line_01.gif"></td>
			</tr>
			<tr>
				<td height="1" bgcolor="#CCCCCC"></td>
			</tr>
			<tr>
				<td id="list"><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="100">&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr id="title">
							<td height="25" align="center">답변 제목</td>
							<td><input type="text" name="title"></td>
						</tr>
						<tr id="writer">
							<td height="25" align="center">작성자</td>
							<td><input type="text" name="writer"></td>
						</tr>
						<tr id="content">
							<td align="center">내용</td>
							<td><textarea name="content" style=""></textarea></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="30" align="right" style="padding-right: 2px;"><img
					src="/reboard/images/write_btin.gif" width="61" height="20"
					onClick="reply()"> <a href="list.jsp"><img
						src="/reboard/images/list_btn.gif" width="61" height="20"></a></td>
			</tr>
			<tr>
				<td height="1" bgcolor="#CCCCCC"></td>
			</tr>
			<%@include file="/reboard/inc/footer.jsp"%>
		</table>
	</form>
</body>
</html>

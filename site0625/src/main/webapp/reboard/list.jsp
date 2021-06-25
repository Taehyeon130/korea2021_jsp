<%@page import="com.koreait.site0625.model.domain.ReBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.koreait.site0625.model.reboard.dao.MybatisReBoardDAO"%>
<%@page import="com.koreait.site0625.model.reboard.dao.ReBoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	ReBoardDAO reBoardDAO = new MybatisReBoardDAO();
%>
<%
	List<ReBoard> boardList = reBoardDAO.selectAll();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style>
#box{border:1px solid #CCCCCC}
#title{font-size:9pt;font-weight:bold;color:#7F7F7F;돋움}
#category{font-size:9pt;color:#7F7F7F;돋움}
#keyword{
	width:80px;
	height:17px;
	font-size:9pt;
	border-left:1px solid #333333;
	border-top:1px solid #333333;
	border-right:1px solid #333333;
	border-bottom:1px solid #333333;
	color:#7F7F7F;돋움
}
#paging{font-size:9pt;color:#7F7F7F;돋움}
#list td{font-size:9pt;}
#copyright{font-size:9pt;}
a{text-decoration:none}
img{border:0px}
</style>
<%@include file="/reboard/inc/lib.jsp" %>
</head>
<body>
<table id="box" align="center" width="603" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td colspan="5"><img src="/reboard/images/ceil.gif" width="603" height="25"></td>
  </tr>
  <tr>
    <td height="2" colspan="5" bgcolor="#6395FA"><img src="/reboard/images/line_01.gif"></td>
  </tr>
  <tr id="title" align="center">
    <td width="50" height="20">번호</td>
    <td width="303" height="20">제목</td>
    <td width="100" height="20">글쓴이</td>
    <td width="100" height="20">날짜</td>
    <td width="50" height="20">조회수</td>
  </tr>
  <tr>
    <td height="1" colspan="5" bgcolor="#CCCCCC"></td>
  </tr>
	<tr>	
		<td colspan="5" id="list">
		  <table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <%for(ReBoard reboard :boardList){ %>
		    <tr align="center" height="20px" onMouseOver="this.style.background='#FFFF99'" onMouseOut="this.style.background=''">
			  <td width="50">1</td>
			  <td width="303" style="text-align:left">
				  <a href="detail.jsp?reboard_id=<%=reboard.getReboard_id()%>"><%=reboard.getTitle() %></a>
			  </td>
			  <td width="100"><%=reboard.getWriter() %></td>
			  <td width="100"><%=reboard.getRegdate().substring(0,10) %></td>
			  <td width="50"><%=reboard.getHit() %></td>
		    </tr>
			<tr>
				<td height="1" colspan="5" background="/reboard/images/line_dot.gif"></td>
			</tr>
			<%} %>
		  </table>		</td>
	</tr>
  <tr>
    <td id="paging" height="20" colspan="5" align="center">[1][2][3][4][5][6][7][8][9][10]</td>
  </tr>
  <tr>
    <td height="20" colspan="5" align="right" style="padding-right:2px;">
	<table width="160" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="70">
          <select name="select" id="category">
            <option>제목</option>
            <option>내용</option>
            <option>글쓴이</option>
          </select>        </td>
        <td width="80">
          <input name="textfield" id="keyword" type="text" size="15">        </td>
        <td><img src="/reboard/images/search_btn.gif" width="32" height="17"></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="30" colspan="5" align="right" style="padding-right:2px;"><a href="write.jsp"><img src="/reboard/images/write_btin.gif" width="61" height="20" border="0"></a></td>
  </tr>
  <tr>
    <td height="1" colspan="5" bgcolor="#CCCCCC"></td>
  </tr>
  <%@include file="/reboard/inc/footer.jsp" %>
</table>
</body>
</html>

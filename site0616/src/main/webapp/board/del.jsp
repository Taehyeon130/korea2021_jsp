<%@page import="site0616.board.model.dao.BoardDAO"%>
<%@ page  contentType="text/html; charset=UTF-8"%>
<%! 
	BoardDAO boardDAO = new BoardDAO();
%>
<%
	
	int result = boardDAO.delete(Integer.parseInt(request.getParameter("board_id")));

	out.print("<script>");
	if(result==0){
		out.print("alert('삭제실패');");
		out.print("history.back();");
	}else{
		out.print("alert('삭제성공');");
		out.print("location.href='/board/list.jsp';");
	}
	out.print("</script>");

%>
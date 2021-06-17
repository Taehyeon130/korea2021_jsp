<%@page import="site0616.board.model.dao.BoardDAO"%>
<%@page import="site0616.model.domain.Board"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%!
	BoardDAO boardDAO = new BoardDAO(); //서블릿의 service() 안에 작성한 것임
 %>
    
<%
	//파라미터 받기!!
	request.setCharacterEncoding("utf-8");//파라미터에 대한 인코딩..
	String title=request.getParameter("title"); //html에 명시한 파라미터 명
	String writer=request.getParameter("writer"); 
	String content=request.getParameter("content"); 
	
	//오라클에 넣기!!
	BoardDAO boardDAO = new BoardDAO(); //서블릿의 service() 안에 작성한 것임
	
	//VO생성하여 인수로 넘기자!!
	Board board = new Board(); //empty상태
	board.setTitle(title);
	board.setWriter(writer);
	board.setContent(content);
	
	int result = boardDAO.insert(board);
	
	out.print("<script>");
	if(result==0){
		out.print("등록 실패<br>");
		out.print("history.back();");
	
	}else{
		out.print("alert('등록성공');");
		out.print("location.href='/board/list.jsp';");
	}
	out.print("</script>");
	
%>    
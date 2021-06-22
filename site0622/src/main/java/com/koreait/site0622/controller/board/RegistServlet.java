package com.koreait.site0622.controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0622.model.board.dao.MybatisBoardDAO;
import com.koreait.site0622.model.domain.Board;

//등록을 처리하는 서블릿 정의 
public class RegistServlet extends HttpServlet{
	MybatisBoardDAO boardDAO;
	
	public void init() {
		boardDAO = new MybatisBoardDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 전송한 파라미터 받기
		request.setCharacterEncoding("utf-8");//파라미터 인코딩 처리(한글 안깨짐)
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		int result = boardDAO.insert(board);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(result<1) {
			out.print("실패");
		}else {
			//out.print("성공");
			response.sendRedirect("/board/list.jsp");
			//int x=6; //여기까지도 실행부는 옴
		}
	}
}

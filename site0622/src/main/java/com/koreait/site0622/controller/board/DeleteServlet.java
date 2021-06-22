package com.koreait.site0622.controller.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0622.model.board.dao.MybatisBoardDAO;
import com.koreait.site0622.util.message.MessageObject;

//게시물 삭제 요청을 처리하는 서블릿 정의
public class DeleteServlet extends HttpServlet{
	
	MybatisBoardDAO boardDAO;
	MessageObject messageObject;
	
	public void init(ServletConfig config) throws ServletException {
		boardDAO = new MybatisBoardDAO();
		messageObject = new MessageObject();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_id = request.getParameter("board_id");
		int result = boardDAO.delete(Integer.parseInt(board_id));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if(result==0) {
			out.print(messageObject.getMsgBack("삭제 실패"));
		}else {
			out.print(messageObject.getMsgURL("삭제 성공","/board/list.jsp"));
		}
	}
}

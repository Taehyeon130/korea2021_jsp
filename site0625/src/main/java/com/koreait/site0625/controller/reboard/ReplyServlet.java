package com.koreait.site0625.controller.reboard;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0625.model.domain.ReBoard;
import com.koreait.site0625.model.reboard.dao.MybatisReBoardDAO;
import com.koreait.site0625.model.reboard.dao.ReBoardDAO;
import com.koreait.site0625.util.message.MessageObject;

//답변등록 요청을 처리하는 서블릿
public class ReplyServlet extends HttpServlet{
	
	ReBoardDAO reboardDAO;
	MessageObject obj;
	
	public void init(ServletConfig config) throws ServletException {
		reboardDAO = new MybatisReBoardDAO();
		obj = new MessageObject();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//답변에 필요한 파라미터 받기
		request.setCharacterEncoding("utf-8");
		
		int team = Integer.parseInt(request.getParameter("team"));
		int step= Integer.parseInt(request.getParameter("step"));
		int depth= Integer.parseInt(request.getParameter("depth"));
		String title = request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		//vo에 답기
		ReBoard reboard = new ReBoard();
		reboard.setTeam(team);
		reboard.setStep(step);
		reboard.setDepth(depth);
		reboard.setTitle(title);
		reboard.setWriter(writer);
		reboard.setContent(content);
		
		
		//답변 등록을 위한 메서드 호출(자리 확보+답변등록)
		int result = reboardDAO.reply(reboard);
		
		//출력처리
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(result==0) {
			out.print(obj.getMsgBack("답변등록 실패"));
		}else {
			out.print(obj.getMsgURL("답변 등록 성공","/reboard/list.jsp"));
		}
	}
}

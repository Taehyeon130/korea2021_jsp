package com.koreait.site0622.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0622.model.domain.Member;
import com.koreait.site0622.model.member.dao.MemberDAO;
import com.koreait.site0622.model.member.dao.MybatisMemberDAO;
import com.koreait.site0622.util.message.MessageObject;

//아이디 중복 체크 전용 서블릿
public class CheckIdServlet extends HttpServlet{
	MemberDAO memberDAO;
	MessageObject messageObject;
	
	public void init() throws ServletException {
		memberDAO = new MybatisMemberDAO();
		messageObject = new MessageObject();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 넘겨받아 member 테이블에 이미 존재하는지 여부를 체크해보자!
		String user_id= request.getParameter("user_id");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//out.print("념겨받은 아이디는 "+user_id+"<br>");
		Member member = memberDAO.getMemberById(user_id); //다형성
		
		/*
		if(member ==null) {
			//회원가입 진행
			//동기방식에 적절한 응답정보
			//out.print(messageObject.getMsgURL("사용가능한 아이디 입니다","/member/signup.jsp"));
		}else {
			//경고 거절
			//동기방식에 적절한 응답정보
			//out.print(messageObject.getMsgBack("이미 사용중인 아이디 입니다"));
		}
		*/
		
		//비동기에 적절한 응답 보내기
		if(member==null) {
			out.print(0);
		}else {
			out.print(1);
		}
	}
}

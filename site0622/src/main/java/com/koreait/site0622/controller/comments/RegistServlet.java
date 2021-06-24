package com.koreait.site0622.controller.comments;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0622.model.comments.dao.CommentsDAO;
import com.koreait.site0622.model.comments.dao.MybatisCommentsDAO;
import com.koreait.site0622.model.domain.Comments;

//댓글 등록 요청 처리 서블릿(클라이언트의 요청이 비동기방식이므로, 디자인을 결과로 보내서는 안됨!! 처리 결과만..
//왜? 전체 새로고침이 일어나지 않는 방식이라 디자인을 보낼 의미가 없슴, 즉 전체 페이지 변환이 목적이 아니라, 
//페이지의 일부의 변경이 목적임..)
public class RegistServlet extends HttpServlet{
	
	CommentsDAO commentsDAO;

	public void init() throws ServletException {
		//commentsDAO = new JdbcCommentsDAO();
		commentsDAO = new MybatisCommentsDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기	
		request.setCharacterEncoding("utf-8");

		String msg=request.getParameter("msg");
		String cwriter=request.getParameter("cwriter");
		int news_id=Integer.parseInt(request.getParameter("news_id"));
		
		//VO에 담기 
		Comments comments = new Comments();
		comments.setMsg(msg);
		comments.setCwriter(cwriter);
		comments.setNews_id(news_id);
		
		//DAO에게 일 시키기 
		int result = commentsDAO.insert(comments);//MybatisCommentsDAO로써 일을 한다 (다형성)polymorphism
		
		//응답객체로부터 출력스트림 얻기
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
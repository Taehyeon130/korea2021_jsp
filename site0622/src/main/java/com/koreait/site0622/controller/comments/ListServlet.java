package com.koreait.site0622.controller.comments;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.site0622.model.comments.dao.CommentsDAO;
import com.koreait.site0622.model.comments.dao.JdbcCommentsDAO;
import com.koreait.site0622.model.domain.Comments;

//댓글 목록 요청을 처리하는 서블릿
public class ListServlet extends HttpServlet{
	CommentsDAO commentsDAO;
	
	public void init() throws ServletException {
		commentsDAO = new JdbcCommentsDAO();
		//commentsDAO = new MybatisCommentsDAO();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기 
		int news_id=Integer.parseInt(request.getParameter("news_id"));
		
		List<Comments> commentsList = commentsDAO.selectByNewsId(news_id);
		
		//뭘 보내야 하나???
		//VO를 클라이언트에 보내기 위해 문자열화 시키자
		//이때 이 문자열화 된 데이터는 구조화시키기 위해
		//json으로 구성하자!!
		//이렇게 하면, 이 데이터를 받는자는 json파싱을
		//하면 된다..단 보낼때 contentType json형식으로 보내면 
		//클라이언트는 파싱할 필요조차 없다..
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		StringBuilder sb=new StringBuilder();

		sb.append("{");
		sb.append("\"commentsList\":[");
		
		for(int i=0;i<commentsList.size();i++) {
			Comments comments = commentsList.get(i);
			sb.append("{");
			sb.append("\"comments_id\":"+comments.getComments_id()+",");
			sb.append("\"msg\":\""+comments.getMsg()+"\",");
			sb.append("\"cwriter\":\""+comments.getCwriter()+"\",");
			sb.append("\"cdate\":\""+comments.getCdate()+"\",");
			sb.append("\"news_id\":"+comments.getNews_id());
			if(i<commentsList.size()-1) {
				sb.append("},");
			}else {
				sb.append("}");
			}
		}
	
		sb.append("]");
		sb.append("}");
		
		out.print(sb.toString()); //클라이언트에 보낼 응답 컨텐츠 구성
	}
	
}
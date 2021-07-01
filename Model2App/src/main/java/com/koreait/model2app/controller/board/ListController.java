package com.koreait.model2app.controller.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.model2app.controller.Controller;
import com.koreait.model2app.model.board.dao.BoardDAO;
import com.koreait.model2app.model.board.dao.JdbcBoardDAO;

//Board의 요청 중 목록 요청을 처리하는 하위 컨트롤러
public class ListController implements Controller{

	BoardDAO boardDAO;
	public ListController() {
		//boardDAO = new MybatisBoardDAO();
		boardDAO = new JdbcBoardDAO();
	}
	
	//3단계 : 알맞는 객체 일 시키기 4단계 : 결과가 있다면 결과 저장
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List list=boardDAO.selectAll();
		
		//4단계 : 저장
		request.setAttribute("boardList", list);
	}


	public String getViewName() {
		return "/result/board/list";
	}
	
	public boolean isForward() {
		return true;
	}

}

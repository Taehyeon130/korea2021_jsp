package com.koreait.site0622.model.board.dao;

import java.util.List;

import com.koreait.site0622.model.domain.Board;

//모든 BoardDAO들이 반드시 구현해야할 메서드를 정의해놓는 인터페이스

public interface BoardDAO {
	public List selectAll();
	public Board select(int board_id);
	public int insert(Board board);
	public int update(Board board);
	public int delete(int board_id);
}

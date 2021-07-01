package com.koreait.model2app.model.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.model2app.model.domain.Board;
import com.koreait.model2app.model.mybatis.MybatisConfigManager;

public class MybatisBoardDAO implements BoardDAO{

	MybatisConfigManager configManager =MybatisConfigManager.getInstance();
	@Override
	public List selectAll() {
		SqlSession sqlSession = configManager.getSession();
		List list = sqlSession.selectList("Board.selectAll");
		configManager.closeSession(sqlSession);
		return list;
	}


	public int insert(Board board) {
		SqlSession sqlSession = configManager.getSession();
		int result = sqlSession.insert("Board.insert",board);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

	
	public Board select(int board_id) {
		SqlSession sqlSession = configManager.getSession();
		Board board = sqlSession.selectOne("Board.select",board_id);
		configManager.closeSession(sqlSession);
		return board;
	}

	public int update(Board board) {
		SqlSession sqlSession = configManager.getSession();
		int result = sqlSession.update("Board.update",board);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

	@Override
	public int delete(int board_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}

package com.koreait.site0625.model.reboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.site0625.model.domain.ReBoard;
import com.koreait.site0625.model.mybatis.MybatisConfigManager;

public class MybatisReBoardDAO implements ReBoardDAO{
	//쿼리문 수행객체를 얻기위한 SqlSessionFactory를 보유한 매니저 선언
	MybatisConfigManager configManager=MybatisConfigManager.getInstance();
	
	//등록 후, pk를 반환하는 메서드
	public int insert(ReBoard reboard) {
		SqlSession sqlSession = configManager.getSession();//세션얻기
		
		System.out.println("넣기 전 reboard_id는 "+reboard.getReboard_id()); //0
		
		int result=sqlSession.insert("ReBoard.insert", reboard); //게시물 넣고
		reboard.setTeam(reboard.getReboard_id());//얻어진 pk를 team에 적용하기!
		sqlSession.update("ReBoard.updateTeam", reboard); //team수정하자

		sqlSession.commit();
		
		System.out.println("넣은 후 reboard_id는 "+reboard.getReboard_id());//현재 세션에서의 최신 pk
		
		configManager.closeSession(sqlSession);//세션닫기
		return result;
	}

	
	public List selectAll() {
		SqlSession sqlSession = configManager.getSession();//세션얻기
		List list = sqlSession.selectList("ReBoard.selectAll");
		configManager.closeSession(sqlSession);//세션닫기

		return list;
	}

	
	public ReBoard select(int reboard_id) {
		SqlSession sqlSession = configManager.getSession();//세션얻기
		ReBoard reboard = sqlSession.selectOne("ReBoard.select",reboard_id);
		
		configManager.closeSession(sqlSession);//세션닫기
		return reboard;
	}

	@Override
	public int update(ReBoard reboard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int reboard_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public int updateStep(ReBoard reboard) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int reply(ReBoard reboard) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
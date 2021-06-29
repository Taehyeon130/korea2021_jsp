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


	//답변 등록
	public int reply(ReBoard reboard) {
		SqlSession sqlSession = configManager.getSession();
		//result 값 의미?  이 쿼리문에 의해 영향을 받은 레코드 수 반환(에러를 말하는 것이 아님)
		int result =0;
		try {
			sqlSession.update("ReBoard.updateStep",reboard); //자리확보
			//따라서 개발자가 따져보아야할 사항은 result가 아닌 에러가 나지 않은 경우에 아래의 답변을 등록해야함
			sqlSession.update("ReBoard.reply",reboard);//답변등록			
			sqlSession.commit();	
			result =1;
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		configManager.closeSession(sqlSession);
		return result;
	}	
}
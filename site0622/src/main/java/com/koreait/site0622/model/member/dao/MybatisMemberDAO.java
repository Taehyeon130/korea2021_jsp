package com.koreait.site0622.model.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.site0622.model.domain.Member;
import com.koreait.site0622.model.mybatis.MybatisConfigManager;

public class MybatisMemberDAO implements MemberDAO{

	MybatisConfigManager configManager = MybatisConfigManager.getInstance();
	
	public Member getMemberById(String id) {
		//쿼리를 직접 수행하지 않고 mybatis의 SqlSession을 이용하여 수행!
		SqlSession sqlSession = configManager.getSession(); //쿼리 수행 객체인 SqlSession얻기!!
		Member member = sqlSession.selectOne("Member.getMemberById", id);
		configManager.closeSession(sqlSession);
		return member;
	}


	public int regist(Member member) {

		return 0;
	}

	
	public int delete(Member member) {
	
		return 0;
	}

	
	public List selectAll() {

		return null;
	}


	public Member select(int member_id) {

		return null;
	}


	public int update(Member member) {

		return 0;
	}

}

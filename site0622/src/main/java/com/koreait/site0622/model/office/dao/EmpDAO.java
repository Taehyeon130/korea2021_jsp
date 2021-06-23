package com.koreait.site0622.model.office.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.site0622.model.mybatis.MybatisConfigManager;

public class EmpDAO {
	MybatisConfigManager configManager = MybatisConfigManager.getInstance();
	
	//모든 레코드 가져오기
	public List selectAll() {
		SqlSession sqlSession = configManager.getSession();
		List list = sqlSession.selectList("Emp.selectAll");
		configManager.closeSession(sqlSession);
		return list;
	}
}

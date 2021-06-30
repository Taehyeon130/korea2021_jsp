package com.koreait.tourapp.model.store.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.tourapp.model.domain.Store;
import com.koreait.tourapp.model.mybatis.MybatisConfigManager;

public class MybatisStoreDAO implements StoreDAO{
	MybatisConfigManager configManager = MybatisConfigManager.getInstance();
	
	public int insert(Store store) {
		SqlSession sqlSession =    configManager.getSession();
		int result = sqlSession.insert("Store.insert", store);
		sqlSession.commit();
		configManager.closeSession(sqlSession);
		return result;
	}

	public List selectAll() {
		SqlSession sqlSession =    configManager.getSession();
		List list = sqlSession.selectList("Store.selectAll");
		configManager.closeSession(sqlSession);
		return list;
	}

	@Override
	public Store select(int store_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Store store) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int store_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}

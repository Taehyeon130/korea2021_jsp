package com.koreait.tourapp.model.store.dao;

import java.util.List;

import com.koreait.tourapp.model.domain.Store;

public class JdbcStoreDAO implements StoreDAO{

	
	public int insert(Store store) {
		
		return 0;
	}

	
	public List selectAll() {
	
		return null;
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

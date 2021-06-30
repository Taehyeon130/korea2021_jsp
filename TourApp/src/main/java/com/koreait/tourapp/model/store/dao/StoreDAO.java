package com.koreait.tourapp.model.store.dao;

import java.util.List;

import com.koreait.tourapp.model.domain.Store;

public interface StoreDAO {
	public int insert(Store store);
	public List selectAll();
	public Store select(int store_id);
	public int update(Store store);
	public int delete(int store_id);
	
}

package com.koreait.site0622.model.news.dao;

import java.util.List;

import com.koreait.site0622.model.domain.News;

//이 인터페이스를 준수하여 News를 처리하도록 구현강제목적으로 최상위객체를 선언한다

public interface NewsDAO {
	public int insert(News news); //글 등록
	public List selectAll(); // 모든 레코드 가져오기
	public News select(int news_id); //한건 가져오기
	public int update(News news);//수정
	public int delete(int news_id);//삭제
}

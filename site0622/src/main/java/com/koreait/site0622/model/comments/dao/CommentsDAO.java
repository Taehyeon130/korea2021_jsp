package com.koreait.site0622.model.comments.dao;

import java.util.List;

import com.koreait.site0622.model.domain.Comments;

public interface CommentsDAO {
	public int insert(Comments comments); //등록
	public List selectAll(); //목록가져오기
	public Comments select(int comments_id); //한건가져오기
	public int update(Comments comments);//수정
	public int delete(int comments_id);//삭제
	
	
	public List selectByNewsId(int news_id);//해당 뉴스에 달린 댓글 목록
}

package com.koreait.site0622.model.member.dao;

import java.util.List;

import com.koreait.site0622.model.domain.Member;

public interface MemberDAO {
	public Member getMemberById(String id); //아이디를 통해 해당 회원이 존재하는지 체크하기 위한 메서드 정의
	public int regist(Member member);//화원가입
	public int delete(Member member);//회원삭제
	public List selectAll();//회원목록
	public Member select(int member_id);//1명의 회원 조회
	public int update(Member member);//회원 수정
}

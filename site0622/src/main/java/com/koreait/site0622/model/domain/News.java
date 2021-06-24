package com.koreait.site0622.model.domain;

import lombok.Data;

@Data
public class News {
	private int news_id;
	private String title;
	private int cnt; //댓글 수
	private String writer;
	private String content;
	private String regdate;
	private int hit;
}

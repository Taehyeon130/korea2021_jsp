package com.koreait.site0622.model.domain;

import lombok.Data;

@Data
public class Comments {
	private int comments_id;
	private String msg;
	private String cwriter;
	private String cdate;
	private int news_id;
}

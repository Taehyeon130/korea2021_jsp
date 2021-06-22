package com.koreait.site0622.model.domain;

import lombok.Data;

@Data
public class Board {
	private int board_id;
	private String title;
	private String writer;
	private String regdate;
	private String content;
	private int hit;
}

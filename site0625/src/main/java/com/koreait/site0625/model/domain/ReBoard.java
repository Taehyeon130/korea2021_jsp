package com.koreait.site0625.model.domain;

import lombok.Data;

@Data
public class ReBoard {
	private int reboard_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	private int team;
	private int step;
	private int depth;
}

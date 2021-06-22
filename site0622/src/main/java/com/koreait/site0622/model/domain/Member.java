package com.koreait.site0622.model.domain;

import lombok.Data;

@Data
public class Member {
	private int member_id;
	private String user_id;
	private String password;
	private String name;
	private String regdate;
}

package site0621.board.model.domain;

import lombok.Data;

//자바 또는 웹개발을 하다보면 @등장
// @는 Annotation이라고 하며 jdk5버전부터 등장
//주 용도는 클래스코드 안에서 부가적인 설정, 설명을 하기 위한 메타정보, 일종의 주석
//즉 프로그램에서 사용되는 주석, 메타정보

@Data
public class Board {
	private int board_id;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
	
	
}

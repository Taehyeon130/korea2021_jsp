package com.koreait.mvcframework.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//모든 하위 컨트롤러가 정의해야할 메서드를 정의한다!!
//즉, 앞으로 정의될 모든 컨트롤러들은 반드시 이 인터페이스를 구현해야함
public interface Controller {
	//컨트롤러의 요청 처리 메서드
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	//어떤 뷰를 보여줘야 할지를 결정하는 메서드
	public String getViewName();
}
	

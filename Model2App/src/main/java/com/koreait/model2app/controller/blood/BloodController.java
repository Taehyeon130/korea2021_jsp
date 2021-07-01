package com.koreait.model2app.controller.blood;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.model2app.controller.Controller;
import com.koreait.model2app.model.blood.BloodService;

//하위 컨트롤러 - 혈액형요청을 처리하는 전담컨트롤러 클래스
public class BloodController implements Controller{
	BloodService service;
	
	public BloodController() {
		service = new BloodService();
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//혈액형에 대한 판단을 처리...
		//3단계 - 알맞는 로직객체에 일 시킨다
		String blood = request.getParameter("blood"); //파라미터 꺼내기
		String msg = service.getAdvice(blood);
		
		//4단계 - 결과가 있다면 결과를 저장한다
		request.setAttribute("msg", msg); //아직 응답이 처리되지 않은 시점이므로 하위와 상위가 공유하고 있는 request객체에 데이터를 넣어두자
		//이렇게 하면 상위가 이 데이터를 꺼낼수 있다
		
	}


	public String getViewName() {
	
		return "/blood/result";
	}
	
}

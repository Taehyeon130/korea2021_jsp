package com.koreait.mvcframework.controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.mvcframework.controller.Controller;
import com.koreait.mvcframework.model.movie.MovieService;

//영화에 대한 요청을 처리하는 컨트롤러
public class MovieController implements Controller{	
	MovieService service;
		
	public MovieController() {
		service = new MovieService();
	}
	
	public void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String movie = request.getParameter("movie"); //파라미터 받기!!
		
		//로직은 재사용가능성이 있으므로 컨트롤러에 소속시키지 말고 별도의 물리적 파일로 분리
		//3단계 : 알맞는 로직객체에 일시킴
		String msg = service.getAdvice(movie);
		
		//결과를 request객체에 심기
		//4단계 : 보여줄 결과가 있다면 결과를 request에 저장!!
		request.setAttribute("msg", msg);
		
		//여기서 디자인 처리가 가능하긴 하지만 유지보수성은 떨어짐
		//디자인은 별도의 jsp에서 담당하기로 한다!!
		//별도의 결과페이지인 result.jsp로 포워딩을 통해 결과를 가지고 가자!!!
	}

	public String getViewName() {
		return "/movie/result.jsp";
	}
}

package com.koreait.model2app.model.movie;

//영화에 대한 조언을 판단하는 모델객체(플랫폼에 중립적 따라서 웹, 응용 모두 사용 가능 == 재사용성)
public class MovieService {
	public String getAdvice(String movie) {
		String msg = null;
		
		if(movie.equals("미션임파서블")) {
			msg="톰크루즈 잘생김";
		}else if(movie.equals("크루엘라")) {
			msg = "엠마스톤 이쁨";
		}else if(movie.equals("어벤져스")) {
			msg = "3000";
		}else if(movie.equals("토이스토리")) {
			msg = "내 영화";
		}
		return msg;
	}
}

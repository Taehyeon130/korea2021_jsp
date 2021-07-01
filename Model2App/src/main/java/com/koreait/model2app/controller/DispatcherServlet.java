package com.koreait.model2app.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//웹클라이언트의 모든 요청을 받는 유일한 진입점 서블릿
//요청을 분석하여 어떤 하위컨트롤러가 요청을 전담할지를 결정짓고 
//해당 하위컨트롤러가 업무를 마친 후엔 결과를 다시 클라이언트에게 전달 즉 응답을 처리함
//1. 요청을 받는다  2. 요청을 분석 5. 응답정보이용 결과보여주기 
public class DispatcherServlet extends HttpServlet{
	//아래의 객체들을 적어도 분석하기 전에는 미리 메모리에 올라와 있어야함(생성자 또는 init을 통해 처리)
	
	Properties props; //java.util 컬렉션 프레임웍 객체 중 Map의 자식
	FileReader reader; //프로퍼티스 객체는 자체적으로 파일을 접근할 수 없기 때문에 파일스트림이 필요함
	
	
	public void init(ServletConfig config) throws ServletException {
		props = new Properties();
		try {
			//파일의 경로는 개발자의 육안이 아니라 프로그래밍 적으로 얻어와야 이 웹어플리케이션을 윈도우 아닌 다른 플랫폼에서 실행할 때 문제가 되지 않음
			//jsp의 application 내장객체가 필요한 시점임
			//하지만 현재 코드는 서블릿이므로 application내장객체의 자료형을 개발자가 알아야함
			//ServletContext!!! (어플리케이션의 전역적 정보를 가진 객체)
			ServletContext context = config.getServletContext(); //application 내장객체의 원형
			
			//유지보수성을 높이려면 설정정보등을 자바코드 안에 두기보다는 외부 설정파일에 두어서 변경하기 쉽게 처리하는 방식이 일반적(JNDI가 대표적)
			String realpath = context.getRealPath(config.getInitParameter("contextConfigLocation"));
			reader = new FileReader(realpath);
			props.load(reader);//프로퍼티스 객체가 스트림을 이용하는 시점! 즉 파일을 검색하기 위한 준비 끝
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//하위 컨트롤러에게 request,response객체를 전달하기 전에 공통적인 기능이 있다면 상위컨트롤러에서 처리해줘야 코드 중복을 피할 수 있음
		request.setCharacterEncoding("utf-8");
		
		//2단계 - 요청을 분석(uri분석) , 더이상 if문은 사용하지 않는다 (Map으로 대체)
		String uri = request.getRequestURI();
		//if문 대신, props파일 탐색하기
		//이렇게 매요청마다 처리할 로직을 전담 객체를 1:1로 부여하는 방식을 가키려 command pattern이라 한다(by GOF)
		String className = props.getProperty(uri);
		System.out.println(uri+" 요청에 동작할 클래스 명 "+className);
		Controller controller=null;
		//클래스 이름을 이용하여 클래스 로드 하기
		try {
			Class controllerClass = Class.forName(className);
			//파일에 명시된 클래스명을 이용하여 동적으로 인스턴스를 생성하는 방법 == 팩토리 패턴이라함
			controller = (Controller)controllerClass.newInstance(); //인스턴스 생성(하위컨트롤러 생성)
			controller.execute(request, response);//하위컨트롤러 동작!
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		//5단계 : 응답정보를 이용한 응답처리 = 결과 보여주기
		//결과는 MVC중 View가 담당하므로 현재 파일과는 다른 jsp에서 처리
		//주의) 응답을 하면 네트워크 끊기고 요청프로세스가 종료되므로 응답을 하지 않고 원하는 jsp 자원에 포워딩
		String viewName = controller.getViewName();
		//넘겨받은 viewName을 이용하여 다시 mapping파일 검색 하기
		String viewPage = props.getProperty(viewName);// /blood/result key값에 대응되는 result.jsp 반환		
		
		//포워딩일 경우
		if(controller.isForward()) {
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response); //포워딩 시작			
		}else {
			//다시 재접속을 명령하는 경우 redirect == location.href
			response.sendRedirect(viewPage); //지정한 url로 다시 재접속할 것을 클라이언트에게 명령
		}
		
		
	}
	//서블릿의 생명주기 메서드 중 서블릿 소멸 시 호출되는 destroy()재정의
	public void destroy() {
		if(reader!=null) {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

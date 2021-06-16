package site0616.test;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//현재 클래스는 그냥 클래스이므로 웹컨테이너에서 해석 및 실행되기 위해서는 서블릿으로 정의해야한다
public class MyServlet extends HttpServlet{
	//아래의 메서드는 서블릿의 인스턴스가 생성되자 마자, 웹컨테이너인 톰캣에 의해 초기화 되기 위한 용도로 사용된다.
	//그리고  init메서드의 매개변수로 전달되는 ServletConfig객체는 말 그래도 서블릿의 환경정보를 담고 있는 객체이다!
	//그리고 이 ServletConfig객체는 톰캣에 의해 생성되어 서블릿에게 전달되어 진다.
	public void init(ServletConfig config) throws ServletException {
		String msg = config.getInitParameter("msg");
		System.out.println(msg);
		
	}
	
	//요청을 처리하는 메서드
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	//클라이언트의 요청이 Get방식일 경우 동작하는 메서드, 이 메서드는 service메서드에 의해 호출된다!!
	//이때 정의된 요청,응답 매개변수도 service메서드의 것을 전달받게 된다. 즉 실제적으로 업무를 처리하는 메서드는 바로 doXXX이다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 메서드에서 요청을 처리합니다");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 메서드에서 요청을 처리합니다");
	}
	
	public void destroy() {
		System.out.println("bye");
	}
}

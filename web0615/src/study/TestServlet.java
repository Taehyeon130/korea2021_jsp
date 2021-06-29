package study;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;

//서블릿만이 javaEE기반의 서버에서 해석 및 실행되어질 수 있으므로, 
//이 클래스를 서블릿으로 정의하자!!
public class TestServlet extends HttpServlet{
	//요청에 대한 응답을 처리해보자!! service() 메서드가 요청 및 응답을 처리

	//node.js  와 마찬가지로 클라이언트의 요청을 처리하려면, 어떤 요청정보를 가지고 클라이언트가
	//요청했는지에 대한 정보를 가진, 요청객체를 이용해야 한다!!
	//참고로 인터페이스인 HttpServletRequest 객체는 , 요청이 들어올때 자동 생성되어 내용이
	//서버에 의해 채워지므로, 개발자가 그냥 쓰기만 하면 된다!!
	//들어온 요청에 대한 응답정보 및 스트림을 가진 응답객체로 매개변수로 넘겨받는다. 
	
	//즉 요청을 처리하는 것은 들어온 요청과 그 요청에 대한 응답까지의 매카니즘을 포함한다!!
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html"); //mimetype 지정 
		response.setCharacterEncoding("utf-8");//스트림을 뽑기 전에 미리 인코딩처리 
		PrintWriter out=response.getWriter();//문자 기반의 출력스트림을 반환하는 메서드!!
		out.print("this is my second Sevlet !!");
	}
}
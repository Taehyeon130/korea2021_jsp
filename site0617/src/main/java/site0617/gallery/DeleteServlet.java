package site0617.gallery;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import site0617.model.gallery.dao.GalleryDAO;

//jsp를 이용해서도, 업무를 처리할 수 있지만, 공부 목적으로 일부러 서블릿으로 처리해보자!
public class DeleteServlet extends HttpServlet{
	ServletContext context; //현재 서블릿이 실행되고 있는 웹어플리케이션 영역(맥락)
	GalleryDAO galleryDAO = new GalleryDAO();
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext();
	}
	
	//doXXX형 메서드로 처리해보자!!
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받기!!
		//파라미터 인코딩 필요하지 않다!(why? 넘겨받는 파라미터는 한글이 포함될 일이 없다!)
		String gallery_id = request.getParameter("gallery_id");
		String filename = request.getParameter("filename");
		
		//jsp에서는 out이 이미 내장 객체로 지원되지만 서블릿은 개발자가 주도하여 모든걸 일일이 처리해야한다
		response.setContentType("text/html;charset=UTF-8"); //jsp에서의 page 지시영역
		PrintWriter out = response.getWriter();
		out.print("gallery_id"+gallery_id+"<br>");
		out.print("filename"+filename+"<br>"); 
		
		//DB에서 삭제
		int result = galleryDAO.delete(Integer.parseInt(gallery_id));
		if(result!=0) { //삭제가 되었다면 파일도 삭제하자
			//파일을 삭제하려면 결국 파일을 제어해야한다... 자바에서 파일을 제어하기 위해제공되는 객체가 File클래스
			//개발자가 실제 이 웹 어플리케이션이 시스템의 어느 경로에 있는지 알 필요 없이 어플리케이션의 루트를 기준으로 경로를 표현하려면
			//개발자가 아닌 시스템에 의해 즉 프로그래밍적으로 경로를 얻는 방법이 있다.  
			//그러기 위해서는 현재 웹 어플리케이션의 정보를 가진 객체를 이용하면 된다.
			String path = context.getRealPath("/data");
			//out.print("이 서블릿이 실행중인 어플리케이션의 디렉토리 중 data의 경로는 "+path+"<br>");
			File file = new File(path+"/"+filename); //삭제 대상이 되는 파일의 full경로로 파일 클래스의 인스턴스 생성!!
			//이 시점부터 해당 파일을 핸들링 할 수 있다. 우리는 지우기!!
			if(file.delete()) { //파일삭제가 성공이라면...
				out.print("<script>");
				out.print("alert('삭제완료');");
				out.print("location.href='/gallery/list.jsp';");
				out.print("</script>");
			};
			
			
		}
	}
}
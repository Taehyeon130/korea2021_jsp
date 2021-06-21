<%@page import="site0617.model.domain.Gallery"%>
<%@page import="site0617.model.gallery.dao.GalleryDAO"%>
<%@page import="site0617.util.FileManager"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page  contentType="text/html; charset=UTF-8"%>
<%!GalleryDAO galleryDAO = new GalleryDAO();%>
<%

	//jsp에서는 서블릿에서 사용되는 어려 객체들을 내장객체 형태로 즉 이름을 이미 부여해놓은 형태로 지원한다!
	//HttpServletRequest : request
	//HttpServletResponse : response
	//PrintWriter : out
	//ServletContext : application
	
	//멀티파트 객체는 생성자의 호출만으로 업로드가 발생!!
	String path =application.getRealPath("/data");
	int maxSize = 2*1024*1024;
	String encoding ="utf-8";
	
	MultipartRequest multi = new MultipartRequest(request,path,maxSize, encoding);
	
	//유저가 업로드한 원본 파일명
	String myfile = multi.getOriginalFileName("myfile"); //새롭게 업로드된 원본 파일명
	String filename = multi.getParameter("filename"); //hidden에 숨겨진 기존 파일명
	
	String title = multi.getParameter("title");
	String writer = multi.getParameter("writer");
	String content = multi.getParameter("content");
	String gallery_id = multi.getParameter("gallery_id");
	
	//사용자가 새로운 파일의 업로드를 희망할 경우
	if(myfile!=null){
		out.print("교체 원함<br>");
		//기존 파일삭제 + 새로운 업로드된 파일의 파일명을 현재 날짜 조합을 이용하여 rename
		File oldFile = new File(path+"/"+filename);
		if(oldFile.delete()){
			File newFile = multi.getFile("myfile"); //이미 data 디렉토리에 업로드된 파일(아직 현재 날짜 조합으로 처리 X)
			long time = System.currentTimeMillis();
			String ext = FileManager.getExt(myfile);
			boolean result = newFile.renameTo(new File(path+"/"+time+"."+ext));
			if(result){ //파일명 교체가 완료되었다면 쿼리문 수행
				Gallery gallery = new Gallery();
				gallery.setTitle(title);
				gallery.setWriter(writer);
				gallery.setContent(content);
				gallery.setFilename(time+"."+ext); //주의 - 새로운 파일명
				gallery.setGallery_id(Integer.parseInt(gallery_id));
				
				galleryDAO.update(gallery); //쿼리 실행				
			}
		}
	}else{
		out.print("교체 원하지 않음<br>");
		Gallery gallery = new Gallery();
		gallery.setTitle(title);
		gallery.setWriter(writer);
		gallery.setContent(content);
		gallery.setFilename(filename); //주의 - 기존 파일명
		gallery.setGallery_id(Integer.parseInt(gallery_id));
		
		galleryDAO.update(gallery); //쿼리 실행			
	}
	
	//서블릿만 사용할때는 아래처럼 반드시 가야하지만
	//jsp를 사용할 경오 html과 혼용이 가능하므로스크립틀릿 바깥쪽에 명시하는 것이 더 편리함
//	out.print("<script>");
//	out.print("중간생략!!");
//	out.print("</script>");
	
%>
<script>
alert('수정완료');
location.href="/gallery/list.jsp";
</script>


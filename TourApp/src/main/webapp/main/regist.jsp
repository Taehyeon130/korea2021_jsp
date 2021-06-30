<%@page import="com.koreait.tourapp.model.store.dao.MybatisStoreDAO"%>
<%@page import="com.koreait.tourapp.model.store.dao.StoreDAO"%>
<%@page import="com.koreait.tourapp.util.FileManager"%>
<%@page import="com.koreait.tourapp.model.domain.Store"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<%!
	StoreDAO storeDAO = new MybatisStoreDAO();
%>
<%
	//service(HttpServletRequest request, HttpServletResponse response)
	//HttpServletRequest --> jsp에서 request 내장객체로 지원 
	//HttpServletResponse --> jsp에서 response 내장객체로 지원
	//ServletContext --> jsp에서 application 내장객체로 지원 
	request.setCharacterEncoding("utf-8");
	
	//클라이언트가 encoding을  multipart/form-data로 즉 바이너리 파일을 포함시켜 전송하므로 
	//파일 업로드를 처리하기 위해서는 개발자가 스트림등으로 세부처리를 해야하는데 , 업무가 커진다..
	//해결책) 전담 업로드 라이브러리인 업로드 컴포넌트를 이용하자!! 
	
	//파일용량, 파일저장위치 기타 설정을 위해서는 DiskFileItemFactory라는 설정 객체가 필요하다!!
	DiskFileItemFactory factory=new DiskFileItemFactory();
	//개발자가 실제 경로를 하드코딩하지 않기 위해서는 현재 어플리케이션의 전역적 정보를 가진 객체인 ServletContext의 getRealPath()
	//을 이용하면 되는데,...현재 이 파일은 서블릿이 아니므로, 난감하다..
	String realPath=application.getRealPath("/data"); //ServletContext의 getRealPath()호출과 같다!!!
	
	factory.setRepository(new File(realPath));//앞으로 업로드된 파일이 저장될 곳(디렉토리까지만 명시)!!!!!
	factory.setSizeThreshold(2*1024*1024);//2M까지 허용
	ServletFileUpload upload=new ServletFileUpload(factory);//설정 적용과 함께 업로드 객체 생성!!
	
	//각종 파라미터 및 파일저장 처리!
	List<FileItem> items = upload.parseRequest(request);//업로드 분석
	
	Store store = new Store();
	
	for(FileItem item:items){
		if(item.isFormField()){ //일반 텍스트 컴포넌트일 경우
			if(item.getFieldName().equals("title")){
				store.setTitle(item.getString("utf-8"));
			}else if(item.getFieldName().equals("content")){
				store.setContent(item.getString("utf-8"));
			}else if(item.getFieldName().equals("addr1")){
				store.setAddr1(item.getString("utf-8"));
			}else if(item.getFieldName().equals("mapx")){
				store.setMapx(Double.parseDouble(item.getString("utf-8")));
			}else if(item.getFieldName().equals("mapy")){
				store.setMapy(Double.parseDouble(item.getString("utf-8")));
			}
		}else{ //파일 컴포넌트 일 경우
			long time = System.currentTimeMillis(); //파일명
			String ext = FileManager.getExt(item.getName());
			String destName = time+"."+ext;
			item.write(new File(realPath+"/"+destName));
			store.setFirstimage("http://localhost:8888/data/"+destName); //이 시점부터 vo안의 모든 property의 데이터가 채워짐
		}
		
	}
	
	//오라클에 insert!!
	int result = storeDAO.insert(store);
	out.print(result);
	
%>
<%=store.getTitle()%>
<%=store.getContent()%>
<%=store.getAddr1()%>
<%=store.getMapx()%>
<%=store.getMapy()%>
<%=store.getFirstimage()%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page  contentType="text/html; charset=UTF-8"%>
<%
	//스크립틀릿 == service 메서드 영역
	out.print("업로드 처리<br>");

	//클라이언트가 일반폼이 아닌 바이너리 데이터가 포함된 데이터를 전송할 경우 
	//즉 form태그에 enctype을  multipart/form-data로 지정하여 전송할 경우
	//서버에서는 파일을 처리할 수 있는 방법으로 요청을 처리해야한다
	//개발자는 업로드 컴포넌트를 이용하면 된다

	request.setCharacterEncoding("utf-8");
	//String title = request.getParameter("title");
	//String writer = request.getParameter("writer");
	//String content = request.getParameter("content");
	//String myfile = request.getParameter("myfile");
	
	//업로드 처리 객체인 MultipartRequest를 사용해보자!!
	String path = "C:/korea202102_jspworkspace/site0617/src/main/webapp/data";
	MultipartRequest multi = new MultipartRequest(request,path); //생성자 호출에 의해 업로드가 완료
	out.print("업로드 완료");
	//out.print(title+"<br>");
	//out.print(writer+"<br>");
	//out.print(content+"<br>");
	//out.print(myfile+"<br>");
	
%>
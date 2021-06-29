<%
/*[1] page 지시 영역은 현재 페이지에 대한 응답정보의 헤더영역을 구성한다
	node.js의 response.writeHead()의 역할과 동일
*/
%>

<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import ="java.sql.DriverManager"%>
<%@ page import ="java.sql.Connection"%>
<%@ page import ="java.sql.PreparedStatement"%>
<%!
	//[2] 선언부 영역으로써, 멤버 영역이다. 즉 멤버 영역이므로 멤버변수와 멤버 메서드를 정의할 수 있다.
	//선언부는 느낌표를 붙여야한다.
	String url ="jdbc:mysql://localhost:3306/javase?characterEncoding=UTF-8";
	String user = "root";
	String pass = "1234";
	Connection con;
	PreparedStatement pstmt;
%>
<%
	//[3] 스크립틀릿 영역
	//여기에 작성한 코드는 메서드에 작성한 코드로 간주되므로 jsp에서 사용할 주요 로직 등을 작성한다!!
	//out.print("hello");

	//클라이언트가 전송한 파라미터들을 받아서 변수에 보관해보자!!\
	//클라이언트가 서버에 현재 regist.jsp를 요청할때 전송한 파라미터를 받아보자
	//jsp는 기본저긍로 개발자가 생성하지 않아도 이미 시스템에 의해 생성된 내장 객체를 지원하는데 특히 클라이언트가 전송한 요청 정보와 관련된 작업을 위해서는
	//request내장객체를 이용하면 된다.... request에는 클라이언트의 모든 요청과 관련된 정보가 들어있다.
	request.setCharacterEncoding("utf-8"); //파라미터에 대한 UTF-8 인코딩 지정

	String user_id = request.getParameter("user_id");
	String password = request.getParameter("password");
	String name = request.getParameter("name");

	//out.print()란 클라이언트에게 최종적으로 응답시 응답내용을 구성할 문자열을 지정할 수 있다.
	out.print(user_id+"<br>");
	out.print(password+"<br>");
	out.print(name+"<br>");

	//mysql에 넣자!!!
	/*
	1. 드라이버 로드
	2. 접속
	3. 쿼리수행
	4. 접속해제
	*/
	
	//jsp의 스크립틀릿 영역은 이미 내부적으로 예외처리가 되어있기 때문에 스크립틀릿에서는 예외처리를 강요하지 않는다.
	//만일필요하다면 개발자가 명시하면된다
	//1단계 : 드라이버로드
	Class.forName("com.mysql.jdbc.Driver");
	out.print("드라이버로드 성공");

	//2단계 : 접속
	con = DriverManager.getConnection(url,user,pass);
	if(con==null){
		out.print("접속 실패<br>");
	}else{
		out.print("접속 성공<br>");

		//쿼리문 작성
		String sql = "insert into member(user_id, password, name) values(?,?,?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,user_id);
		pstmt.setString(2,password);
		pstmt.setString(3, name);

		//쿼리문 수행
		int result = pstmt.executeUpdate(); //DML
		if(result<1){
			out.print("입력실패");
		}else{
			out.print("입력성공");
		}

		//접속 해제
		con.close();
	}
%>
<%@ page  contentType="text/html; charset=UTF-8"%>
<%
	//이 jsp를 접근하는 클라이언트에 쿠키가 존재하지 않는다면 이 클라이언트의 이 jsp를 
	//처음 접근한다고 보아야하므로 톰캣이 이 클라이언트에게 세션ID를 생성하여 발급하게 되는데
	// id를 출력해본다.
	String sessionId = session.getId(); // 톰캣이 클라이언트에게 발급한 세션 고유번호(최초의 접근이라고 판단할 경우 발급)
	out.print("서버가 발급한 세션 아이디는 "+sessionId);
	session.setAttribute("name", "tiger"); //세션에 데이터 넣기!
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
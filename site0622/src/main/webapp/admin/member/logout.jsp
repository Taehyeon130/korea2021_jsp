<%@ page  contentType="text/html; charset=UTF-8"%>
<%
	//현재 클라이언트가 사용중인 session객체를 무효화 시키자
	//즉 기존 세션을 더 이상 사용못하게 처리하자
	
	session.invalidate();
%>
<script>
alert("로그아웃 처리되었습니다");
location.href="/member/login.jsp";
</script>
<%@page import="com.koreait.site0625.util.message.MessageObject"%>
<%@page import="com.koreait.site0622.model.member.dao.MybatisMemberDAO"%>
<%@page import="com.koreait.site0622.model.member.dao.MemberDAO"%>
<%@page import="com.koreait.site0622.model.domain.Member"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%!
	MemberDAO memberDAO=new MybatisMemberDAO();
	MessageObject obj=new MessageObject();
%>
<%
	//서블릿으로 변경된후, service메서드의 영역 
	//파라미터들을 넘겨받아 db에 insert!!
	request.setCharacterEncoding("utf-8"); //request내장객체는 HttpServletRequest가 자료형임
%>
<jsp:useBean id="member" class="com.koreait.site0622.model.domain.Member"/>
<jsp:setProperty property="*" 	name="member"/>
<%
	//db에 넣기
	int result = memberDAO.regist(member);
	if(result==0){
		out.print(obj.getMsgBack("회원등록 실패")); //out은 출력스트림 내장객체
	}else{
		out.print(obj.getMsgURL("회원가입을 축하드립니다", "/member/login.jsp"));
	}
%>
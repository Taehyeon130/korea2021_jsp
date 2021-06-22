<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}
input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}
input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
input[type=button]:hover {
  background-color: #45a049;
}
.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	//버튼들에 이벤트 연결하기 
	$("#bt_check").click(function(){
		checkId();	
	});
	
	$("#bt_regist").click(function(){
		regist();
	});
});
//회원가입 전에 아이디 중복여부 체크
function checkId(){
	//동기방식으로 전송하게 되면 메인 실행부가 서버의 응답이 있을 때까지 대기상태에 빠지고 넘겨받은 컨텐츠를 이용하여 화면 전체를
	//갱신하기 때문에 깜빡거림 효과 즉 새로고침 효과가 나버림
	//해결책) 별도의 비동기 객체를 이용하여 메인 대신 비동기 객체가 서버와의 통신을 담당하고, 서버가 보내준 컨텐츠를
	//메인 실행부에 전달하면 메인 실행부는 이 컨텐츠를 DOM을 이용한 접근 방법으로 HTML의 일부를 수정만 하면 되기 때문에
	//전체 페이지가 아닌 부분적인 페이지의 갱신만 이루어진다... 즉 새로고침이 일어나지 않는다!!(웹의 한계 극복)
	/*
	$("form").attr({
		"action":"/member/idcheck",
		"method":"post"
	});
	$("form").submit(); //전송 
	*/
	
	//순수한 비동기 객체인 XMLHttpRequest를 직접 사용할 수 도 있으나  처리 코드가 너무 번거롭기때문에
	//자바스크립트를 단순화 시켜놓은 프레임워크인 jquery의 ajax기능을 활용해보자
	$.ajax({
		
	});
}
function regist(){
	
}
</script>
</head>
<body>

<h3>회원가입</h3>

<div class="container">
  <form>
    <input type="text" name="user_id" 			placeholder="Your ID.." style="width:70%">
    <input type="button" value="ID중복확인" id="bt_check">
    
    <input type="text" name="password" 		placeholder="Your Password..">
    <input type="text" name="name" 			placeholder="Your Name..">
    <input type="button" value="회원등록" id="bt_regist">
  </form>
</div>

</body>
</html>
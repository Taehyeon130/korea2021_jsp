<%@ page  contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style type="text/css">
select{
	width:400px;
	font-size:30px;
}
</style>

<script>
function send(){
	form1.action="/blood.do"; //url에 의미를 담자(의미를 담을수 있는 수단임)
	form1.method="post";
	form1.submit();
}
</script>

</head>
<body>
	<form name="form1">	
		<select name="blood">
			<option>자신의 혈액형을 선택</option>
			<option value="A형">A형</option>
			<option value="B형">B형</option>
			<option value="O형">O형</option>
			<option value="AB형">AB형</option>
		</select>
	</form>
	
	<button onClick="send()">결과 보기</button>
</body>
</html>
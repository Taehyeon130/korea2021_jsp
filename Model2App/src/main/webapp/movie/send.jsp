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
	form1.action="/movie.do";
	form1.method="post";
	form1.submit();
}
</script>

</head>
<body>
	<form name="form1">	
		<select name="movie">
			<option>영화 선택</option>
			<option value="미션임파서블">미션임파서블</option>
			<option value="크루엘라">크루엘라</option>
			<option value="어벤져스">어벤져스</option>
			<option value="토이스토리">토이스토리</option>
		</select>
	</form>
	
	<button onClick="send()">결과 보기</button>
</body>
</html>
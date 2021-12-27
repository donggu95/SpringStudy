<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<select name="blood">
			<option>영화를 선택하세요</option>
			<option value="어벤져스">어벤져스</option>
			<option value="분노의질주">분노의질주</option>
			<option value="아이언맨">아이언맨</option>
			<option value="스파이더맨">스파이더맨</option>
		</select>
	</form>
	<p>
	
	<button onClick="send()">전송</button>
		
</body>
</html>
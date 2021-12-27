<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function send(){
	form1.action="/blood/result.jsp";
	form1.method="post";
	form1.submit();
}
</script>
</head>
<body>

	<form name="form1"> <!-- 장점은 부모자식관의 관계로 접근 할 수 있다. -->
		<select name="blood">
			<option>혈액형을 선택하세요</option>
			<option value="A">A</option> <!-- 실제 데이터는 value에 넣는다. -->
			<option value="B">B</option>
			<option value="AB">AB</option>
			<option value="O">O</option>
		</select>
	</form>
	<p>
	
	<button onClick="send()">전송</button>
		
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function regist(){
	//폼을 서버로 전송한다!!
	form1.action="/excel/upload.jsp";
	form1.method="post";
	form1.encoding="multipart/form-data";
	form1.submit();
}
</script>
</head>
<body>

	<form>
		<input type="file" name="filename">
		<button type="button" onClick="regist()">엑셀파일등록</button>
	</form>
	
</body>
</html>
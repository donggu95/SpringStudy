<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
여기는 블러드의 결과페이지<br>
<%
	String msg=(String)request.getAttribute("result");
	out.print(msg);
%>
<%//=msg %>
</body>
</html>
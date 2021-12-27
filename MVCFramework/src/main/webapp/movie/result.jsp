<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="yellow">
여기는 블러드의 결과페이지 <br>
<%
	// 스크립틀릿 영역은 이 jsp가 서블릿으로 변경될때의 service(request, response) 메서드 영역이고
	// 서비스 메서드의 request 객체가 전달되어 지는 경우도 있다..(포워딩시..)
	String result=(String)request.getAttribute("result");
	out.print(result);
%>
</body>
</html>
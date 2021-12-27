<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
/*
request와 같은 변수가 왜 선언도 한적도 없는데 사용이 가능한가?? 그것도 .찍고 객체로서
사실은 이 영역은 이 jsp가 서블릿으로 변결될때의 service() 메서드 영역이다.
jsp는 사실상 서블릿인데 이를 숨기기 위해 어쩔수 없이 service(request, response) 두 객체를
jsp에서 사용하려면, 누군가는 이 객체들의 이름을 부여해야 한다...

application내장객체의 자료형은 ServletContext 이다!!
*/
String path = application.getRealPath("/WEB-INF.mapping.data");
out.print(path);
%>

</body>
</html>






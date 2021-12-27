<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="pool.dbcp.DBCPPool"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%

	DBCPPool pool=DBCPPool.getInstance();

	Connection con=DriverManager.getConnection("jdbc:apache:commons:dbcp:");
	out.print(con);//커넥션 풀로부터 얻어온 Connection 객체를 출력
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
나의 첫 이클립스기반 프로젝트 성공~!!
</body>
</html>
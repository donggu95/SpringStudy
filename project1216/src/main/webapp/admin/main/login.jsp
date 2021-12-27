<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//이 서블릿에 최초접속 이거나 예전에 할당받은 세션id가 존재하지 않을 경우
	//톰켓은 새로운 세션 객체를 생성하고, 고유한 아이디를 부여
	///HttpSession (서블릿으로 코딩할때) --> session (jsp내장객체로 이용할때)
	
	String sid=session.getId(); //고양이가 이 세션객체에 할당한 고유값
	out.print("당신이 할당받은 세션아이디는 "+sid);
%>
<%@page import="com.koreait.project1207.store.model.StoreDAO"%>
<%@page import="com.koreait.project1207.domain.Store"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!StoreDAO storeDAO = new StoreDAO(); %>

<%
	//파라미터를 넘겨받아, db에 넣기!!
	request.setCharacterEncoding("utf-8"); //파라미터 인코딩
	String title=request.getParameter("title");
	String sido=request.getParameter("sido");
	String score=request.getParameter("score");
	
	//넘겨받은 파라미터는 DTO에 담자!!
	Store store = new Store(); //empty 상태
	store.setTitle(title);
	store.setSido(sido);
	store.setScore(Integer.parseInt(score));
	
	int result=storeDAO.insert(Store);
	out.print(result);
%>
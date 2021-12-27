<%@page import="com.koreait.project1207.store.model.StoreDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! StoreDAO storeDAO = new StoreDAO(); %>
<%
	//파라미터를 넘겨받아 db삭제
	String store_id = request.getParameter("store_id");
	
	int result=storeDAO.delete(Integer.parseInt(store_id));
	out.print(result);
%>
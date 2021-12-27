<%@page import="com.koreait.project1207.store.model.StoreDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! StoreDAO storeDAO = new StoreDAO();%>
<jsp:useBean id="store" class="com.koreait.project1207.domain.Store"/>
<jsp:setProperty property="*" name="store"/>

<%
	//입력한 파라미터값들을 받아서 db에 수정처리
	//update store set title=?, sido=?, score=? where store_id=?
	
	request.setCharacterEncoding("utf-8");
	int result=storeDAO.update(store);
	
	System.out.print(result);
%>
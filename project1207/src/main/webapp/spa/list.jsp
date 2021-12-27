<%@page import="com.koreait.project1207.domain.Store"%>
<%@page import="java.util.List"%>
<%@page import="com.koreait.project1207.store.model.StoreDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! StoreDAO storeDAO = new StoreDAO(); %>
<%
	//모든 레코드 가져오기!!
	List<Store> storeList = storeDAO.selectAll();
	
	out.print("{");
	out.print("\"storeList\" :[");
	for(int i=0;i<storeList.size();i++){
		Store store=storeList.get(i); //맛집 하나 끄집어 내기 
		
		out.print("{	");
		out.print("\"store_id\":"+store.getStore_id()+", ");
		out.print("\"title\":\""+store.getTitle()+"\", ");
		out.print("\"sido\":\""+store.getSido()+"\",");
		out.print("\"score\":"+store.getScore());
		
		if(i < storeList.size()-1){
			out.print("},");
		}else{
			out.print("}");
		}
	}
	out.print("]");
	out.print("}");

%>
	

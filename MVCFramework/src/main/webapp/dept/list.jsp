<%@page import="com.koreait.mvc.domain.Dept"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//부서 데이터만을 응답정보로 구성하자!!
	List<Dept> deptList = (List)request.getAttribute("deptList");
	
	Gson gson=new Gson();
	String str = gson.toJson(deptList);
	
%>
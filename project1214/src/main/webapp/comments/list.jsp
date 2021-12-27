<%@page import="com.google.gson.Gson"%>
<%@page import="com.koreait.project1214.domain.Comments"%>
<%@page import="java.util.List"%>
<%@page import="com.koreait.project1214.model.repository.MybatisCommentsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! MybatisCommentsDAO mybatisCommentsDAO = new MybatisCommentsDAO(); %>
<%
	String news_id=request.getParameter("news_id");
	// 이따가 dao select * from comments where news_id=?

	List<Comments> commentsList=mybatisCommentsDAO.selectAll(Integer.parseInt(news_id));
	
	// java의 List를 json 배열로 자동변환해줄수 있다면 참 좋겠다...
	// how to convert java list to json using gson
	Gson gson = new Gson();
	String result=gson.toJson(commentsList);
	
	out.print(result);
%>
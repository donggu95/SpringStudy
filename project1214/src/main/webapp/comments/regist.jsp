<%@page import="com.koreait.project1214.model.repository.MybatisCommentsDAO"%>
<%@page import="com.koreait.project1214.domain.Comments"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! MybatisCommentsDAO mybatisCommentsDAO = new MybatisCommentsDAO(); %>
<%
	//넘겨받은 파라미터를 이용하여 댓글등록
	request.setCharacterEncoding("utf-8");
	String news_id = request.getParameter("news_id");
	String msg = request.getParameter("msg");
	String author = request.getParameter("author");
	
	Comments comments = new Comments();
	comments.setNews_id(Integer.parseInt(news_id));
	comments.setMsg(msg);
	comments.setAuthor(author);
	
	int result = mybatisCommentsDAO.insert(comments);
	out.print(result);
%>
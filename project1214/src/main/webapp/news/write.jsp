<%@page import="com.koreait.project1214.model.repository.MybatisNewsDAO"%>
<%@page import="com.koreait.project1214.model.repository.JdbcNewsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="news" class="com.koreait.project1214.domain.News"/>  <!--  new -->
<jsp:setProperty property="*" name="news"/> <!--  파라미터 받기 -->

<%! JdbcNewsDAO jdbcNewsDAO = new JdbcNewsDAO(); %>
<%! MybatisNewsDAO MybatisNewsDAO = new MybatisNewsDAO(); %>

<%
	
	// 파라미터가 채워졌으므로, jdbc 기반의  jdbcDAO 에 일 시키자 !
	//int result = jdbcNewsDAO.insert(news);
	int result = MybatisNewsDAO.insert(news);
	out.print(result);
	
%>
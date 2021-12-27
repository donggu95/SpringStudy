<%@page import="com.koreait.project1214.model.repository.MybatisNewsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<jsp:useBean id="news" class="com.koreait.project1214.domain.News"/>
<%request.setCharacterEncoding("utf-8"); %>
<jsp:setProperty property="*" name="news"/>

<%! MybatisNewsDAO mybatisNewsDAO = new MybatisNewsDAO(); %>
<%
	// 파라미터 4개를 넘겨받아, dao 일 시키기 !
	int result = mybatisNewsDAO.update(news);
	
	out.print("<script>");
	if (result != 0){
		out.print("alert('수정성공');");
		out.print("location.href='/news/content/jsp?news_id="+news.getNews_id()+";");
	} else {
		out.print("alert('수정실패');");
		out.print("history.back();");
	}
	out.print("</script>");
%>
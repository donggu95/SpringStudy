<%@page import="java.util.List"%>
<%@page import="com.koreait.mvc.domain.Notice"%>
<%@page import="com.koreait.mvc1223.util.Pager"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! Pager pager = new Pager();%>
<%
	List<Notice> noticeList = (List)request.getAttribute("noticeList");
	pager.init(noticeList, request);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}
</style>
<script type="text/javascript">
function writeForm(){
	location.href="/notice/write.jsp";
}
</script>
</head>
<body>

	<table>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		<%int curPos=pager.getCurPos(); %>
		<%int num=pager.getNum(); %>
		<%for(int i=1;i<=pager.getPageSize();i++){ %>
		<%if(num<1)break; %>
		<%Notice notice=noticeList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><%=notice.getTitle() %></td>
			<td><%=notice.getWriter() %></td>
			<td><%=notice.getRegdate() %></td>
			<td><%=notice.getHit() %></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5" align="center">
				<%for(int i=pager.getFirstPage();i<=pager.getLastPage();i++){ %>
				<%if(i>=pager.getTotalPage())break; %>
				[<%=i %>]
				<%} %>
			</td>
		</tr>
		<tr>
			<td colspan="5">
				<button onClick="writeForm()"></button>
			</td>
		</tr>
	</table>

</body>
</html>

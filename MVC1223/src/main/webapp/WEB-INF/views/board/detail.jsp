<%@page import="com.koreait.mvc1223.domain.Board"%>
<%@page import="com.koreait.mvc1223.util.Pager"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
   // 여기서 사용되는 request 형님한테 가져온 request이므로, 고인물도 아닌 썩은물 수준
   List<Board> boardList = (List)request.getAttribute("boardList");
	Pager pager=(Pager)request.getAttribute("pager");   
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
	location.href="/board/write";
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
	<%int curPos=pager.getCurPos();%> <!--  get메소드에 ++, -- 불가하니 변수로 빼기 -->
	<%int num=pager.getNum(); %>
	<%for(int i=1; i<=pager.getPageSize(); i++){ %>
	<%if(num < 1) break; %>
    <%Board board = boardList.get(curPos++); %>


	<tr>
		<td><%=num--%></td>
		<td><a href="/board/detail?board_id=<%=board.getBoard_id()%>"><%=board.getTitle()%></a></td>
		<td><%=board.getWriter()%></td>
		<td><%=board.getRegdate()%></td>
		<td><%=board.getHit()%></td>
	</tr>
	<%} %>
	<tr>
		<td colspan="5" align="center">
			<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++){ %>
			<%if(i>=pager.getTotalPage())break; %>
			[<%=i %>]
			<%} %>
		</td>
	</tr>
	<tr>
		<td colspan="5">
			<button onClick="writeForm()">글쓰기</button>
		</td>
	</tr>
</table>

</body>
</html>
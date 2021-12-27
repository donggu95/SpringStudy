<%@ page contentType="text/html;charset=UTF-8"%>
<%
Board board = (Board)request.getAttribute("board"); 
%>
<%=board.getTeam() %>
<%=board.getStep() %><br>
<%=board.getDepth() %><br>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
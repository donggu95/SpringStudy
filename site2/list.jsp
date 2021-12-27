<%@ page contentType="text/html;charset=utf-8"%>
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
<script>
addEventListener("load", function(){
	//DOM이 메모리에 올라오고 나서..
	document.querySelector("button").addEventListner("click",function(){
		location.href="send.jsp";
	});
});
</script>
</head>
<body>

<h2>갤러리 리스트</h2>


<table>
  <tr>
    <th>이미지</th>
    <th>제목</th>
  </tr>

<%while(rs.next()){%>
  <tr>
    <td><img src="/data/<%=rs.getString("filename")%>" width="60px"></td>
    <td><%=rs.getString("title")%></td>
  </tr>
<%}%>
	<tr>
		<td colspan="2">
			<button>게시물 등록</button>
		</td>
	</tr>
</table>

</body>
</html>
<%
rs.close();
pstmt.close();
con.close();
%>
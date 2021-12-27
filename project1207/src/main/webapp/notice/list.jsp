<%@page import="com.koreait.project1207.domain.Notice"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.koreait.project1207.notice.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%! NoticeDAO noticeDAO = new NoticeDAO(); %>
<%
	List<Notice> noticeList=noticeDAO.selectAll();
%>

<%
	int totalRecord=noticeList.size();		//전제조건, db에 따라 언제든지 바뀜... 그래서 변수화 시킴..(바뀌니까!!)
	int pageSize=5;			//페이지당 보여질 레코드 수 
	int totalPage=(int)Math.ceil((float)totalRecord/pageSize);//총 페이지수
	int blockSize=10; //블럭당 보여질 페이지 수(pageSize가 레코드수를 묶은 단위였다면, blockSize는 page들을 묶은 단위)
	int currentPage=1; //현재 내가 보고있는 페이지

	//만일 유저가 페이지 번호를 눌렀다면....
	if(request.getParameter("currentPage") !=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage")); //String --> int 로 변환하여 저장
	}
	int firstPage=currentPage - (currentPage-1)%blockSize; //블럭당 시작페이지
	int lastPage=firstPage + (blockSize-1); // 블럭당 마지막 페이지
	int curPos= (currentPage-1)*pageSize; // 1-0, 2-10, 3-20 각페이지당 시작번호!!
	int num=totalRecord - curPos; //페이지당 시작 번호
	
%>
<%="totalRecord 는 "+totalRecord%><br>
<%="pageSize 는 "+pageSize%><br>
<%="totalPage 는 "+totalPage%><br>
<%="currentPage "+currentPage%><br>
<%="firstPage "+firstPage%><br>
<%="lastPage "+lastPage%><br>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.pageStyle{
	font-weight:bold;
	font-size:15px;
	color:yellow;
}

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
//2006 js 코드를 함수화 시켜 라이브러리로 제공..(제이쿼리)
/* addEventListener("load", function() {
	
}); */

$(function(){
	//CSS의 선택자를 이용할 수 있따..
	$("button").click(function(){
		location.href="/notice/write.jsp";
	});
});
</script>
</head>
<body>

<div class="container">    

<table class="table table-dark">
	<thead>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody>
		<%
			//커서의 위치를 다시 원상복귀
			//rs.beforeFirst();
			//rs.absolute(curPos); // 1page row num 1부터, 2page row num 11부터, 3page row num 21부터..
			
		%>
		<%for(int i=1;i<pageSize;i++){%>
		<%if(num<1)break;%>
		<%Notice notice=noticeList.get(curPos++); //각 요소에 들어있는 DTO를 꺼낸다 그리고 이 DTO 한개가 게시물 레코드 1건을 표현한다.%>
		<tr>
			<td><%=num--%></td>
			<td><a href="/notice/content.jsp?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle() %></a></td>
			<td><%=notice.getWriter() %></td>
			<td><%=notice.getRegdate() %></td>
			<td><%=notice.getHit() %></td>
		</tr>
		<%}%>
		
		<tr>
			<td colspan="5"><button>글등록</button></td>
		</tr>
		
		<tr>
			<td colspan="5" align="center">
			<%if(firstPage-1 > 0){//이전페이지가 있다면....%>
			<a href="/notice/list.jsp?currentPage=<%=firstPage-1%>">이전페이지</a>
			<%}else{%>
				<a href="javascript:alert('이전 페이지가 없습니다');">이전페이지</a>
				<%}%>

			<%for(int i=firstPage;i<=lastPage;i++){%>
				<%if(i > totalPage )break; //페이지번호가 내가 가진 총 페이지를 넘어서면 반복문 중단...%>
				<a href="/notice/list.jsp?currentPage=<%=i%>"  <%if(i==currentPage){%>class="pageStyle"<%}%>><%=i%></a>
			<%}%>
			
			<%if(lastPage+1 > totalPage){//없다면 욕!! %>
				<a href="javascript:alert('다음 페이지가 없습니다.');">다음페이지</a>
			<%}else{ //다음 페이지가 있다면...정상링크...%>
				<a href="/notice/list.jsp?currentPage=<%=lastPage+1%>">다음페이지</a>
			<%}%>

			</td>
		</tr>
	</tbody>
</table>

</div>
</body>
</html>





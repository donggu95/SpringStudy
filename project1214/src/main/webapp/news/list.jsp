<%@page import="com.koreait.project1214.domain.News"%>
<%@page import="java.util.List"%>
<%@page import="com.koreait.project1214.model.repository.MybatisNewsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! MybatisNewsDAO mybatisNewsDAO = new MybatisNewsDAO();  %>

<% //페이징 처리
   List<News> newsList=mybatisNewsDAO.selectAll();

   int totalRecord = newsList.size(); //총레코드 수
   int pageSize=10;//페이지 당 보여질 레코드 수
   int totalPage=(int)Math.ceil((float)totalRecord/pageSize);
   int blockSize=10; //블럭당 보여질 페이지 수
   int currentPage=1;//현재 보고있는 페이지로써 디폴드값은 1이다.
   if(request.getParameter("currentPage")!=null){ //만약 유저가 넘긴 페이지가 있다면 그 페이지를 현재페이지로 삼는다.
      currentPage=Integer.parseInt(request.getParameter("currentPage"));
   }
   int firstPage=currentPage-(currentPage-1)%blockSize;
   int lastPage=firstPage + (blockSize-1);
   int curPos=(currentPage-1)*pageSize;
   int num=totalRecord-curPos; //페이지당 시작 번호
   
   
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.pageNum{
   color:yellow;
   font-weight:bold;
   font-size:16px;
}
</style>

<!-- jQuery library -->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
   src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
$(function(){
   $("button").click(function(){
      location.href="/news/regist.jsp";
   });
})
</script>

</head>
<body>
   <div class="container">
      <table class="table table-dark table-hover" >
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
         <%for(int i = 0; i <pageSize; i++){ %>
         <%if(num<1)break; %>
         <%News news=newsList.get(curPos++); %>
            <tr>
               <td><%=num-- %></td>
               <td><a href="/news/content.jsp?news_id=<%=news.getNews_id()%>"><%=news.getTitle() %></a>
               		  <%if(news.getCnt()>0){ %>[<%=news.getCnt() %>]<%} %>
               </td>		  
               <td><%=news.getWriter() %></td>
               <td><%=news.getRegdate().substring(0,10) %></td>
               <td><%=news.getHit() %></td>
            </tr>
            <%} %>
            <tr>
               <td colspan="5" align="center">
               ◀
               <%for(int i =firstPage;i<=lastPage;i++){ %>
               <%if(i>totalPage) break; %>
                  <a href="/news/list.jsp?currentPage=<%= i%>" <%if(currentPage==i){ %> class="pageNum"<%} %>>[<%=i %>]</a>
               <%} %>
               ▶
               </td>
            </tr>
            <tr>
               <td colspan="5">
                  <button>글등록</button>
               </td>
            </tr>
            
         </tbody>
      </table>
   </div>
</body>
</html>
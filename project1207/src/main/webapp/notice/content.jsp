<%@page import="com.koreait.project1207.domain.Notice"%>
<%@page import="com.koreait.project1207.notice.model.NoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! NoticeDAO noticeDAO = new NoticeDAO(); %>
<%
	//글 한건 가져오기
	String notice_id=request.getParameter("notice_id");
	out.print("넘어온 파라미터는" +notice_id);
	
	Notice notice = noticeDAO.select(Integer.parseInt(notice_id));
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
	$($("input[type=button]")[0]).click(function(){//수정 POST
		if(confirm("수정하시겠어요?")){
			$("#form1").attr("action","/notice/edit"); // action 지정
			$("#form1").attr("method","post"); // 전송메소드는 post
			$("#form1").submit(); // 전송
		}
	});

	$($("input[type=button]")[1]).click(function(){//삭제 Post.Get 상관없다.
		if(confirm("삭제하시겠어요?")){
			$("#form1").attr("action","/notice/delete.jsp"); // action 지정
			$("#form1").attr("method","post"); // 전송메소드는 post
			$("#form1").submit(); // 전송
		}
	});

	$($("input[type=button]")[2]).click(function(){//목록 Get
		location.href="/notice/list.jsp";
	});
	
});

</script>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
  <form id="form1">
    <input type="hidden" id="notice_id" name="writer" value="<%=notice.getNotice_id()%>">
  
    <input type="text" id="fname" name="writer" value="<%=notice.getWriter()%>">
    <input type="text" id="lname" name="title" value="<%=notice.getTitle()%>">
    <textarea id="subject" name="content" style="height:200px"><%=notice.getContent() %></textarea>
    <input type="button" value="수정 ">
    <input type="button" value="삭제">
    <input type="button" value="목록 ">
  </form>
</div>

</body>
</html>
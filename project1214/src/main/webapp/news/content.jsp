<%@page import="com.koreait.project1214.domain.News"%>
<%@page import="com.koreait.project1214.model.repository.MybatisNewsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!MybatisNewsDAO mybatisNewsDAO = new MybatisNewsDAO();%>
<%
String news_id = request.getParameter("news_id");
out.print(news_id);

News news = mybatisNewsDAO.select(Integer.parseInt(news_id));
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
   //제이쿼리의 문법 구조는 무엇을(선택자) 어떻게(함수)!
   $("#bt_edit").click(function(){
	   if(confirm("수정하시겠어요?")){
	      //폼을 전송하자 제이쿼리로!
	      
	      $($("form")[0]).attr({
	         "action":"/news/edit.jsp",
	         "method":"post"
	      });
	      $($("form")[0]).submit();
	   }
   });
   
   $("#bt_del").click(function(){
	   if(confirm("삭제하시겠어요?")){
		   var news_id = $("input[name='news_id']").val();
			location.href="/news/del.jsp?news_id="+news_id;
	   }
   });
   
// 댓글 등록 요청
   $("#bt_comment").click(function(){
      // 비동기 요청을 시도할 때, XMLHttpRequest를 직접 사용하면 코드량이 너무 많다...따라서 jquery가 지원하는 Jqeury Ajax를 사용해보자
      $.ajax({
         url : "/comments/regist.jsp",
         type:"post",
         //기존의 form을 이용하는 방법이 아닌, 자체적인 파라미터명을 지정해서 전송하는 방법이다
         data:{
            news_id:<%=news.getNews_id()%>, // 숫자인 경우에는 따옴표 노필요
            msg:$("input[name='msg']").val(), 
            author:$("input[name='author']").val() 
         },
         success:function(result,status,xhr){ // xhr은 XMLHttpRequest, result는 this.responseText
            console.log(result);
         	if(result!=0){
         		getCommentsList();//목록출력 함수 호출
         	}else{
         		alert("댓글 등록 실패");
         	}
         	//입력 폼 초기화
         	$("input[name='msg']").val("");
         	$("input[name='author']").val("");
         },
         error:function(xhr,status,error){
            
         }
      });
   });
   
   
   $("#bt_list").click(function(){
      location.href="/news/list.jsp"
   });
   
   getCommentsList();//이 뉴스기사에 소속된 코멘트 리스트 출력
});

//댓글의 목록을 동적으로 출력
function getCommentsList(){
	//비동기 방식으로 댓글 목록 가져오기
	$.ajax({
		url:"/comments/list.jsp?news_id="+<%=news.getNews_id()%>,
		type:"get",
		success:function(result, status, xhttp){
			//console.log(result.length); //현재로서는 json이 아닌 String 이다. 따라서 파싱 처리하자!!
			var json=JSON.parse(result); //문자열에 불과한 제이슨 표기법 스트링을 실제 json객체로 변환
			console.log("총 댓글 게시판물 수는 "+json.length);
			
			//테이블 출력
			printTable(json);
		}
	});
}

function printTable(json){
	//누구(css선택)를.어떻게(함수)
	//document.getElementById("comments_id").innerHTML=tag;
	var tag="<table width='100%'>";
	for(var i=0; i<json.length;i++){
		var obj=json[i];//제이슨 배열에서 json 객체를 1개를 끄집어 낸다.
		tag+="<tr>";
		tag+="<td>"+obj.msg+"</td>";
		tag+="<td>"+obj.author+"</td>";
		tag+="<td>"+obj.writedate+"</td>";
		tag+="</tr>";
	}
	tag+="</table>"
	
	$("#comments_list").html(tag);//제일 마지막에 누적된 태그문자열 대입!!

}


</script>
</head>
<body>

	<h3>상세보기</h3>

	<div class="container">
		<form>
			<input type="hidden" 	name="news_id" 		value="<%=news.getNews_id()%>">
			<input type="text" name="title" value="<%=news.getTitle()%>">
			<input type="text" name="writer" value="<%=news.getWriter()%>">
			<textarea name="content" style="height: 200px"><%=news.getContent()%></textarea>

			<input type="button" value="수정" id="bt_edit"> 
			<input type="button" value="삭제" id="bt_del"> 
			<input type="button" value="목록" id="bt_list">
		</form>
	</div>
	<div>
		<form>
			<input type="hidden" 	name="news_id" 		value="<%=news.getNews_id()%>">
			<input type="text" name="msg" placeholder="댓글제목입력..." style="width:70%">
			<input type="text" name="author" placeholder="작성자..." style="width:20%">
			<input type="button" value="댓글등록" id="bt_comment">
		</form>
	</div>
	
	<!-- 댓글리스트 영역 : 화면전체x, 부분출력(프런트개발자 업무) -->
	<div id="comments_list"></div>
	
</body>
</html>





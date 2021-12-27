<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String[] sido = {"서울","인천","대구","부산","목포","천안"};
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Single Page Application</title>
<style>
body{margin:0;}

.wrapper{
	background:gold;
	width:1000px;
	height:700px;
	overflow:hidden;
	margin:auto;
}
.input_area{
	width:200px;
	height:100%;
	background:tomato;
	float:left;
}
#list_area {
	width:600px;
	height:100%;
	float:left;
}
.detail_area{
	width:200px;
	height:100%;
	background:orange;
	float:left;
}
</style>

<script>
addEventListener("load", function(){
	var bt_regist = document.getElementById("bt_regist");
	var bt_regist = document.getElementById("bt_edit");
	var bt_regist = document.getElementById("bt_del");
	
	bt_regist.addEventListener("click", function(){
		registAsync();
	});
	
	//수정버튼을 누르면...
	bt_edit.addEventListener("click", function(){
		if(confirm("수정하시겠어요?")){
			editAsync();
		}
	});
	
	//삭제버튼을 누르면...
	bt_del.addEventListener("click", function(){
		if(confirm("삭제하시겠어요?")){
			delAsync();
		}
	});
	
	
	getList();  // 문서가 로드되면 곧바로 비동기로 데이터 가져오기 
});

//비동기 삭제요청
function delAsync(){
	//게시물을 선택하지 않고, 삭제버튼을 누르면 욕!!
	if(form2.store_id.value.length==0){
		alert("게시물을 선택해주세요");
		return;
	}
	
	var xhttp=new XMLHttpRequest();
	xhttp.open("get","/spa/del.jsp?store_id="+form2.store_id.value);
	xhttp.send();
}

//비동기 수정요청
function editAsync(){
	var xhttp = new XMLHttpRequest();  // 실행부 대신 비동기 통신이 가능한 객체 ! 비동기객체
	xhttp.open("post","/spa/edit.jsp");
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     
	    	if(this.responseText == 1) { 
	    	  getList();	
	      } else {
	    	  alert("수정실패");
	      }
	    }
	  };
	var store_id=form2.store_id.value;
	var store_id=
	var title = document.getElementById("title2").value;
	var sido = document.getElementById("sido2").value;
	var score = document.getElementById("score2").value;
	xhttp.send("store_id="+store_id+"&title="+title+"&sido="+sido+"&score="+score);
	
}

// 기존의 전송방식대로 처리해보자 ! (서버에 요청을 !!)
function registSync(){
	var form1 = document.getElementById("form1");
	form1.action="/spa/regist.jsp";
	form1.method="post";
	form1.submit();
	
}
// 비동기 방식으로 요청을 시도하여, 페이지 전부를 갱신하지 말고 서버로부터 가져온 데이터를 이용한 일부 페이지의 갱신만 시도해보자!
// without reloading !!!!
function registAsync(){
	var xhttp = new XMLHttpRequest();  // 실행부 대신 비동기 통신이 가능한 객체 ! 비동기객체
	xhttp.open("post","/spa/regist.jsp");
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      // alert("서버로 부터 받은 데이터는 " + this.responseText);
	      if(this.responseText == 1) {   // 성공 
	    	  // 서버에게 목록을 요청한다. (비동기로 ! 왜? 데이터만 가져올꺼니깐 !!)
	    	  getList();
	      } else {
	    	  alert("등록실패");
	      }
	    }
	  };
	  
	var title = document.getElementById("title").value;
	var sido = document.getElementById("sido").value;
	var score = document.getElementById("score").value;
	
	xhttp.send("title="+title+"&sido="+sido+"&score="+score);
}

// 비동기 방식으로 리스트 요청하기 
function getList() {
	var xhttp = new XMLHttpRequest(); // 비동기 객체 생성   
	xhttp.open("get","/spa/list.jsp"); // 요청 주소 및 메소드 
	
	xhttp.onreadystatechange = function() {  // 벤트 핸들링 
		 if (this.readyState == 4 && this.status == 200) { // 서버의 응답이 성공적으로 도달할 것임 !!
			 // 현재 시점에서는 responserText 자체는 문제열 이었다. 따라서 Json이 보유한 storeList라는 속성을 이해할 수 없다.
			 //console.log(this.responseText.storeList);
		 	 var json =  JSON.parse(this.responseText); // 제이슨 표기로 작성된 문자열을 실제 json객체로 변환해주는 메소드 !
			/* console.log(json.storeList);		 
			console.log(json.storeList.length);		 
			console.log(json.storeList[0]); */		 
		 	 
		 	 
		 	// 화면 일부 출력 !
			printTable(json);		 	
		 }
	}
	xhttp.send();
}

// 리스트에 테이블을 출력한다 !
function printTable(data){
	console.log("매개변수로 전달된 객체는",data);
	var list_area = document.getElementById("list_area");
	var tag = "<table border='1px' width='100%''>";
	tag+= "<tr>";
	tag+= "<td width=70%>제목</td>";
	tag+= "<td width=20%>지역</td>";
	tag+= "<td width=10%>평점</td>";
	tag+= "</tr>";
	
// 반복할 대상 
	 for(var i=0; i<data.storeList.length; i++){
		var obj = data.storeList[i];  // {"store_id" : 1,"title" : "일산맛집","sido" : "일산","score" : 5}
		
		tag+= "<tr>";
		tag+= "<td><a href=\"javascript:getDetail("+obj.store_id+");\">"+obj.title+"</a></td>";
		tag+= "<td>"+obj.sido+"</td>";
		tag+= "<td>"+obj.score+"</td>";
		tag+= "</tr>";
		}
	tag+= "</table>";  
	
	list_area.innerHTML=tag;
}

// 상세보기 구현 
function getDetail(store_id){
		// 데이터 한건을 가져오기 위한 비동기 요청을 시도하자 ! (js자체에서 해결하지 않는 이유는 !? 한번 더 공부하려고 ㅋㅋㅋ;;;)
		var xhttp = new XMLHttpRequest();
		xhttp.open("GET", "/spa/content.jsp?store_id="+store_id);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
			      // 화면에 반영 
			      console.log(this.responseText.title);
			      //결론: this.responseText로 받은 데이터는 json객체는 아니고, json 표기법을 지킨 문자열 상태임..
			      //해결: 객체화되지 않았으므로, .찍고 접근불가 따라서 문자열을 --> json 객체로 변환하자!!
			      var json = JSON.parse(this.responseText);
			      
			      printDetail(json);//화면에 출력!!
			}
		}
		xhttp.send();
		
		//pk값을 히든에 보관해두자(추후 수정 or 삭제시 사용하려고....)
		form2.store_id.value=store_id;
}

function printDetail(data){
	var title2=document.getElementById("title2");
	var sido2=document.getElementById("sido2");
	var score2=document.getElementById("score2");
	
	title2.value=data.title;
	sido2.value=data.sido;
	score2.value=data.score;
}
</script>
</head>
<body>
<!-- 모든 html 태그는 다른 태그요소와의 배치문제에 있어서 block(동일라인에 공존을 허용x), inline 중 하나이다.  -->
	<div class="wrapper">
		<div class="input_area">
			<form id="form1">
				<input type="text" placeholder="제목" id="title">
				<br>
				
				<select id="sido">
					<%for(int i=0; i<sido.length; i++){ %>
					<option value="<%=sido[i] %>"><%=sido[i] %></option>
					<%} %>
				</select>
				<br>
				
				<select id="score">
					<%for(int i=1; i<=5; i++){ %>
					<option value="<%=i %>"><%=i %></option>
					<%} %>
				</select>
				<br>
				
				<button type="button" id="bt_regist">등록</button>
			</form>
		</div>
		
		<div id="list_area">
		
		</div>
		
		<div class="detail_area">
			<form id="form2" name="form2">
				<input type="hidden" id="store_id" name="store_id">
				<input type="text" placeholder="제목" name="title2" id="title2">
				<br>
				<select id="sido2">
					<%for(int i=0; i<sido.length; i++){ %>
					<option value="<%=sido[i] %>"><%=sido[i] %></option>
					<%} %>
				</select>
				<br>
				
				<select id="score2">
					<%for(int i=1; i<=5; i++){ %>
					<option value="<%=i %>"><%=i %></option>
					<%} %>
				</select>
				<br>
				
				<button type="button" id="bt_edit">수정</button>
				<button type="button" id="bt_del">삭제</button>
			</form>
		</div>
	</div>
	
</body>
</html>
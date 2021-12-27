<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%!
	String url="jdbc:mysql://localhost:3307/jsp;
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
%>
<%
	Class.forName("com.mysql.jdbc.Driver");//드라이버로드
	con=DriverManager.getConnection(url, "root","0000");//접속시도 및 접속정보 객체 얻기
	pstmt=con.prepareStatement("select * from gallery order by gallery_id desc");//내림차순 정렬
	rs=pstmt.executeQuery();//select문 수행
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Add icon library -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

.input-container {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  width: 100%;
  margin-bottom: 15px;
}

.icon {
  padding: 10px;
  background: dodgerblue;
  color: white;
  min-width: 50px;
  text-align: center;
}

.input-field {
  width: 100%;
  padding: 10px;
  outline: none;
}

.input-field:focus {
  border: 2px solid dodgerblue;
}

/* Set a style for the submit button */
.btn {
  background-color: dodgerblue;
  color: white;
  padding: 15px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
}

.btn:hover {
  opacity: 1;
}

</style>
<script>
// JS의 최상위 객체인 window, 생략가능 , 사실 alert도 window의 메소드인것...
// body에 onLoad()를 사용해도 되지만 이렇게도 사용가능 !
window.addEventListener("load",function(){
    var bt = document.querySelector("button");   // querySelector();을 이용하면 css 선택자로 사용가능하다
    bt.addEventListener("click",function(){
        // 업로드 !
        send();
    });
});
// 업로드 함수만들기! 클라이언트가 선택한 이미지 및 입력폼 데이터를 서버로 전송하자 !
function send(){
    var form = document.querySelector("form"); // dom을 접근하는 또 다른 방법

    form.action="/upload.jsp"; // 요청 주소
    form.method="post"; // 전송 메소드
    form.encoding="multipart/form-data"; // 폼태그를 이용하여 서버에 데이터 전송 시 입력 폼 뿐만 아니라 바이너리 파일이 포함되어 있다면, 
                                        //복합적인 인코딩 방식을 채택대햐 한다. (필수!!)
    form.submit(); // 전송
}
</script>
</head>
<body>

<form style="max-width:500px;margin:auto">
  <h2>이미지 업로드 폼</h2>
  <div class="input-container">
    <i class="fa fa-user icon"></i>
    <input class="input-field" type="text" placeholder="이미지제목..." name="title">
  </div>
  
  <div class="input-container">
    <i class="fa fa-key icon"></i>
    <input type="file" name="photo">
  </div>

  <button type="button" class="btn">업로드</button> 
  <%-- 버튼태그는 type button을 작성하지 않으면 기본으로 submit임 --%>
</form>

</body>
</html>
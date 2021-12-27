<%@ page import ="java.sql.*"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%!
  // JDBC 삼총사 !
  String url = "jdbc:mysql://localhost:3307/jsp";
  Connection con;
  PreparedStatement pstmt;
  ResultSet rs;
%>
<%
  String notice_id=request.getParameter("notice_id"); // 리스트에서 목록 클릭 시 이 값 결정!
  String sql ="select * from notice where notice_id=" + notice_id;

  out.print(sql);

  Class.forName("com.mysql.jdbc.Driver"); // 드라이버로드
  con = DriverManager.getConnection(url,"root","0000"); // 접속
  out.print(con);

  pstmt = con.prepareStatement(sql);  // 객체생성
  rs = pstmt.executeQuery();// 쿼리실행
  rs.next(); // 커서 한 칸 이동 (데이터가 0또는1이므로 while문이 필요없다.)

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
<script src="https://cdn.ckeditor.com/4.17.1/standard/ckeditor.js"></script>
<script>
  // 문서내의 content라는 이름을 가진 요소에 편집기를 적용!
  function init(){
	CKEDITOR.replace( "content" );

	var bt_del = document.getElementById("bt_del");
	console.log("메모리에 올라온 버튼 톰은", bt_del);

	bt_del.addEventListener("click", functuon(){
		if(confirm("삭제하실래요?")){
			location.href="
		}
	});
  }

  function edit(){
    // var result = confirm("수정하시겠어요?");
    // console.log("결과는",result);
    if (confirm("수정하시겠어요?")) {
      // 수정시도, 서버에 수정을 요청 (직접 불가)
      var form1 = document.getElementById("form1");
      form1.action="/notice/edit.jsp"; // 수정을 담당하는 페이지 요청
      form1.method="post";
      form1.submit(); // 전송메소드
    }
  }
  
 //문서내에 있는 삭제버튼을 누르면...
var bt_del = document.getElementById("bt_del");
console.log("메모리에 올라온 버튼 돔은 ", bt_del);
</script>

</head>
<!-- body부분이 전부 실행 된 후 자바스크립트 구현되게 하는 기능 onLoad -->
<body onLoad="init()">  

<h3>글 상세보기</h3>

<div class="container">
  <form id="form1">
	<input type="hidden"  name="notice_id" value="<%=rs.getInt("notice_id")%>">

    <label for="fname">First Name</label>
    <input type="text" id="fname" name="title" value="<%=rs.getString("title")%>">
    <%-- input태그는 값을 넣을때 value를 사용 --%>

    <label for="lname">Last Name</label>
    <input type="text" id="lname" name="writer" value="<%=rs.getString("writer")%>">

    <label for="subject">내용</label>
    <textarea id="subject" name="content" style="height:200px" ><%=rs.getString("content")%></textarea>
    <%-- textarea태그는 닫는 태그가 있으므로 value가 필요없고 >< 사이에 넣으면 된다. --%>

    <!-- submit은 버튼의 기능 + 자동전송이 이미 포함되어있다. -->
    <input type="button" value="수정하기" onClick="edit()">
    <input type="button" value="삭제하기" id="bt_del">
    <input type="button" value="목록보기" onClick="location.href='/notice/list.jsp'">
  </form>
</div>

</body>
</html>
<%
	rs.close();
	pstmt.close();
	con.close();
%>
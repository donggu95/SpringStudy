<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import ="java.sql.*"%>
<%!
    String url="jdbc:mysql://localhost:3307/jsp";
    String id="root";
    String pass="0000";

    Connection con; // 접속정보 객체
    PreparedStatement pstmt; // 쿼리수행 객체
    ResultSet rs; // 레코드 결과집합 다루는 객체
%>
<%
  // mysql 데이터 가져오기
  Class.forName("com.mysql.jdbc.Driver"); // 드라이버 로드
  out.print("드라이버 로드<br>");

  con = DriverManager.getConnection(url,id,pass); // 접속시도
  pstmt = con.prepareStatement("select * from notice"); // 쿼리객체 생성
  rs = pstmt.executeQuery(); // select문 수행 !!

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
<script>
  /* 
  이 영역은 프로그래밍 영역입니다.!!
  JS에서 개발자가 정의한 함수를 가리켜 사용자 정의 함수라 한다. 또한 이때 사용하는 키워드가 바로 function 키워드!
  */
  function getList(){
    //alert("나 불렀어???");
    location.href="/notice/write.jsp";
  }
</script>
</head>
<body>

<h2>게시판 목록</h2>
<table>
  <tr>
    <th>No</th>
    <th>제목</th>
    <th>작성자</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
<%while(rs.next()){%>
  <tr>
    <td></td>
    <td><a href="/notice/content.jsp?notice_id=<%=rs.getInt("notice_id")%>><%=rs.getString("title")%></a></td>
    <td><%=rs.getString("title")%></td>
    <td><%=rs.getString("writer")%></td>
    <td><%=rs.getString("regdate")%></td>
    <td><%=rs.getInt("hit")%></td>
  </tr>
<%}%>
  <tr>
    <td colspan="5">
      <button onClick="getList()">글등록</button>
    </td>
  </tr>
</table>

</body>
</html>
<%
  if(rs!=null) rs.close();
  if(pstmt!=null) pstmt.close();
  if(con!=null) con.close();
%>
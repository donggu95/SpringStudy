<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import ="java.sql.DriverManager"%>
<%@ page import ="java.sql.Connection"%>
<%@ page import ="java.sql.PreparedStatement"%>
<%!
    String url ="jdbc:mysql://localhost:3307/jsp";
    
    Connection con;
    PreparedStatement pstmt;
%>
<%
    // 클라이언트가 전송한 파라미터들을 넘겨받아, mysql에 반영하기 !
    request.setCharacterEncoding("utf-8"); // 파라미터 받기 전에 인코딩 처리!
    String notice_id = request.getParameter("notice_id");
    String title = request.getParameter("title");
    String writer = request.getParameter("writer");
    String content = request.getParameter("content");

    out.print(title+"<br>");
    out.print(writer+"<br>");
    out.print(content+"<br>");

    // DB 연동
    Class.forName("com.mysql.jdbc.Driver");
    con = DriverManager.getConnection(url,"root","0000");
    String sql = "update notice set title=?, writer=?, content=? where notice_id=?";
    pstmt = con.prepareStatement(sql);
        pstmt.setString(1,title);
        pstmt.setString(2,writer);
        pstmt.setString(3,content);
        pstmt.setInt(4,Integer.parseInt(notice_id));

        int result = pstmt.executeUpdate(); // DML 이므로 executeUpdate(); 사용, 이 쿼리에 의해 영향을 받은 레코드 개수를 반환( 1:성공 0:실패)
        if(result==0){
            out.print("수정실패");
        } else {
            out.print("수정성공");
            // 다시 상세보기 요청
            // response.sendRedirect("/notice/content.jsp?notice_id=notice_id"+notice_id);  // 본 글이 넘어와야 하므로 파라미터로 넘겨온 notice_id get방식으로 입력 
            out.print("<script>");
            out.print("alert('수정완료')");
			out.print("location.href='/notice/content.jsp?notice_id="+notice_id+"';");
            out.print("</script>");
        }

    pstmt.close();
    con.close();
%>
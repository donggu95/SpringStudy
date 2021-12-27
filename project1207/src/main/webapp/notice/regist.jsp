<%@page import="com.koreait.project1207.domain.Notice"%>
<%@page import="com.koreait.project1207.notice.model.NoticeDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	NoticeDAO noticeDAO = new NoticeDAO();
%>
<%-- <%!
	String url="jdbc:mysql://localhost:3306/jsp";
	
	Connection con;
	PreparedStatement pstmt;
%> --%>
<%
	request.setCharacterEncoding("utf-8");

// 파라미터를 받아, mysql에 insert - 상투적인 코드 !! 
	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	
	
	System.out.println("이것은 어디에 출력이 되나???"); // javaSE , 콘솔에 출력
	
	/* con = DriverManager.getConnection(url,"root","12341234");
	String sql = "insert into notice(title,writer,content) values (?,?,?)"; 

	pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, writer);
		pstmt.setString(3, content);

	int result = pstmt.executeUpdate(); // 쿼리 실행 */
	
	// 클라이언트가 전송한 파라미터를 DTO에 담아서 전달하자 !
	Notice notice = new Notice(); // DTO 인스턴스 생성, empty상태 
	notice.setWriter(writer);
	notice.setTitle(title);
	notice.setContent(content);
	
	int result = noticeDAO.insert(notice);
	
	if (result == 1){
			out.print("<script>alert('글 등록 완료'); location.href='/notice/list.jsp';</script>");
	} else {
			out.print("<script>alert('글 등록 실패'); 'history.back();</script>");
	}
	
	/* pstmt.close();
	con.close(); */

%>
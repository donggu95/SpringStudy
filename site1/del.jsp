<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.sql.*"%>
<%!
	String url;
	Connection con;
	PreparedStatement pstmt;
%>
<%
	//레코드 1건 삭제
	int notice_id=Integer.parseInt(request.getParameter("notice_id"));

	String sql="delete from notice where notice_id="+notice_id;
	out.print(sql);
	
	//드라이버 로드
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection(url, "root", "0000");
	pstmt=con.prepareStatement(sql);//쿼리객체 생성
	int result = pstmt.executeUpdate(); //DML
	
	out.print("<script>");
	if(result==0){
		out.print("alert('삭제실패')");
		out.print("history.back();");

	}else{
		out.print("alert('삭제성공')");
		out.print("location.href='/notice/list.jsp';");
	}
	out.print("</script>");

	pstmt.close();
	con.close();
%>

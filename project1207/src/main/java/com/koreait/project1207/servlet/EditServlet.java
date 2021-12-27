package com.koreait.project1207.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.project1207.domain.Notice;
import com.koreait.project1207.notice.model.NoticeDAO;

public class EditServlet extends HttpServlet {
	NoticeDAO noticeDAO;
	
	// 생성자가 아니라, 생성한 후, 톰켓으로부터 서블릿으로서 필요한 정보를 넘겨받을 수 있는 초기화 메소드 !
	public void init() throws ServletException {
		noticeDAO = new NoticeDAO();
	}
	
	// 클라이언트 요청 중 post방식의 요청을 처리 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");; // 파라미터에 대한 인코딩 처리 
		// 파라미터 받기 !!
		String notice_id = request.getParameter("notice_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		System.out.println("notice_id = " + notice_id);
		System.out.println("title = " + title);
		System.out.println("writer = " + writer);
		System.out.println("content = " + content);
		
		// update notice set title =? , writer =? , content =? where notice_id =?
		Notice notice = new Notice();  // empty 상태 
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.update(notice);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); // 클라이언트에게 전송할 응답정보를 담게 될 문자기반 출력스트림 
		
		out.print("<script>");
		if(result == 0) {
			out.print("alert('수정실패!');");
			out.print("history.back();");
		} else {
			out.print("alert('수정성공!');");
			out.print("location.href='/notice/content.jsp?notice_id="+notice_id+"';"); // 상세보기 페이지 재요청 !!( 클라이언트의 브라우저가 지정된 url로 재접속하게 됨 !)
			
		}
		out.print("</script>");
	}

}
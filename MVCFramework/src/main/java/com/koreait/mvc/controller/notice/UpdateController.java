package com.koreait.mvc.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.mvc.controller.Controller;
import com.koreait.mvc.domain.Notice;
import com.koreait.mvc.model.repository.notice.NoticeDAO;

//수정 요청을 처리하는 하위컨트롤러 
public class UpdateController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계: 일시키기 
		
		Notice notice = new Notice();
		notice.setNotice_id(Integer.parseInt(request.getParameter("notice_id")));
		notice.setWriter(request.getParameter("writer"));
		notice.setTitle(request.getParameter("title"));
		notice.setContent(request.getParameter("content"));
		
		//4단계: 저장할 것이 없어야 하지만, 예외적으로 현재로서는 detail 페이지서 보여질 데이터를 저장 
		request.setAttribute("notice", notice);
	}

	public String getViewName() {
		return "/result/notice/update";
	}

	public boolean isForward() {
		return true;
	}
	
}
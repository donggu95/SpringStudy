package com.koreait.mvc.controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.mvc.controller.Controller;
import com.koreait.mvc.model.repository.notice.NoticeDAO;

//삭제요청을 처리하는 하위 컨트롤러 
public class DeleteController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3단계) 알맞는 객체에 일시키기
		int notice_id=Integer.parseInt(request.getParameter("notice_id"));
		int result=noticeDAO.delete(notice_id);
		
		//4단계) 없슴
	}

	@Override
	public String getViewName() {
		return "/result/notice/delete";
	}

	@Override
	public boolean isForward() {
		return false;
	}
	
}
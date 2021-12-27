package com.koreait.springmvc1223.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.koreait.springmvc1223.model.service.notice.NoticeService;

//삭제요청을 처리하는 하위 컨트롤러
public class DeleteController implements Controller{
	private NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int result = noticeService.delete(Integer.parseInt(request.getParameter("notice_id")));
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/notice/list");
		
		return mav;
	}

}

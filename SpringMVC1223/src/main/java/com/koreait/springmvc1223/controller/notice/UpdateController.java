package com.koreait.springmvc1223.controller.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.koreait.springmvc1223.domain.Notice;
import com.koreait.springmvc1223.model.service.notice.NoticeService;

//수정 요청을 처리하는 하위 컨트롤러
public class UpdateController implements Controller{
	private NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계: 일시키기
		Notice notice = new Notice();
		notice.setTitle(request.getParameter("title"));
		notice.setWriter(request.getParameter("writer"));
		notice.setContent(request.getParameter("content"));
		notice.setNotice_id(Integer.parseInt(request.getParameter("notice_id")));
		
		int result=noticeService.update(notice);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/notice/detail?notice_id="+notice.getNotice_id());// forwarding이 기본이기 때문에 만일 개발자가 요청을 끊기 위해서는 redirect: 키워드 명시

		return mav;
	}

}

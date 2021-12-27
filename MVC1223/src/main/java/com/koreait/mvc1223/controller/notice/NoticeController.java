package com.koreait.mvc1223.controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.koreait.mvc1223.domain.Notice;
import com.koreait.mvc1223.exception.NoticeException;
import com.koreait.mvc1223.exception.UploadException;
import com.koreait.mvc1223.model.notice.NoticeService;
import com.koreait.mvc1223.util.FileManager;
import com.koreait.mvc1223.util.Pager;

//스프링 버전이 올라갈 수록, 구현강제보다는 개발자의 자율에 맡기는 추세이며 
//xml에 설정하는 대부분의 코드가 자바 어노테이션으로 지원된다..
// 그냥 일반 클래스로 개발할 수 있을까..  POJO(Plain Old Java Object) 예전부터 써오던... 그냥 자바
@Controller
public class NoticeController{
	
	@Autowired
	private NoticeService noticeService;//느슨하게 보유.. 
	
	@Autowired
	private Pager pager;
	
	
	//메서드 정의 자율~~~
	@RequestMapping(value="/notice/write")
	public String writeForm() {
		return "notice/write";
	}
	
	//게시물 목록 
	@RequestMapping(value="/notice/list")
	public ModelAndView getList(HttpServletRequest request) {
		//3단계: 일시키기!!
		List noticeList = noticeService.selectAll();
		
		//4단계: 결과저장 
		ModelAndView mav = new ModelAndView("notice/list"); //setViewName() 메서드 사용과 동일
		mav.addObject("noticeList", noticeList);
		
		pager.init(noticeList, request);
		mav.addObject("pager", pager);
		
		return mav;
	}
	
	//게시물 등록
	@RequestMapping(value="/notice/regist", method=RequestMethod.POST)
	public ModelAndView regist(HttpServletRequest request, Notice notice) {
		System.out.println("당신이 입력한 제목은 "+notice.getTitle());
		
		//3단계: 일 시키기 (게시물 등록= insert + 파일등록)
		String filename=FileManager.saveAsFile(request, notice); //파일 저장 후 , 생성된 파일명을 DTO에 대입
		notice.setFilename(filename);//새롭게 생성된 파일명을 dto에 대입!!
		noticeService.insert(notice);//insert
		
		//4단계: 해당없슴
		ModelAndView mav = new ModelAndView("redirect:/notice/list");
		return mav;
	}
	
	//업로드 테스트 
	@RequestMapping(value="/notice/upload", method=RequestMethod.POST)
	public String uploadTest(HttpServletRequest request, Notice notice) {
		return null;
	}
	
	//한건 가져오기 
	@RequestMapping(value="/notice/detail", method=RequestMethod.GET)
	public ModelAndView select(int notice_id) {
		//3단계
		Notice notice = noticeService.select(notice_id); //pk
		
		//4단계: 저장 
		ModelAndView mav = new ModelAndView();
		mav.addObject("notice", notice);
		mav.setViewName("notice/detail");
		
		return mav;
	}
	
	//수정 요청 처리 
	@RequestMapping(value="/notice/update", method=RequestMethod.POST)
	public ModelAndView update(Notice notice) {
		noticeService.update(notice);
		ModelAndView mav = new ModelAndView("redirect:/notice/detail?notice_id="+notice.getNotice_id());
		return mav;
	}
	
	//삭제 요청 처리 
	@RequestMapping(value="/notice/delete", method=RequestMethod.GET)
	public String delete(int notice_id) {
		noticeService.delete(notice_id);
		
		return "redirect:/notice/list"; //담을게 없다면 String으로 주소를 처리해도 된다..즉 뷰만 처리해도 될때는 String도 허용됨.
	}
	
	
	
	@ExceptionHandler(NoticeException.class)
	public ModelAndView handleException(NoticeException e) {
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("e" , e);
		return mav;
	}
	
	@ExceptionHandler(UploadException.class)
	public ModelAndView handleException(UploadException e) {
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("e" , e);
		return mav;
	}
	
}























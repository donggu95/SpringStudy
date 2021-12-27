package com.koreait.mvc.controller.blood;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.mvc.controller.Controller;
import com.koreait.mvc.model.blood.BloodManager;
// javaEE에서 컨트롤러 역할을 수행할 클래스 정의 
// 왜 서블릿으로 가야 하나?  남은게 없다...그리고 웹상의 요청을 받을 수 있는 능력이 있어야 하므로...
public class BloodController implements Controller{
											/* implements 효과 : 1) 같은 자료형으로 묶어버렸다. 2) 구현강제.. */
	private BloodManager bloodManager;

	public BloodController() {
		bloodManager = new BloodManager();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String blood = request.getParameter("blood");
		
		//3단계) 알맞는 객체에 일 시키기!
		String msg=bloodManager.getAdvice(blood);
		
		//4단계) 형님컨트롤러가 가져갈 수 있도록 결과 저장( 형님 컨트롤러가 접근할 수 있는 메모리 어딘가에)..
		request.setAttribute("result", msg);
	}
	
	//메서드하나 정의하여 형님이 보여줘야할 뷰페이지 정보를 형님에게 부담시키지 말고 (형님 또 분석 if  ㅜㅜ .. )
	//동생이 결정해서 넘겨주면 된다..왜?? 동생이 해당 요청에 대한 전문가니깐... 
	@Override
	public String getViewName() {
		return "/result/blood";
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return false;
	}
}









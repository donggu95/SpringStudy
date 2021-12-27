package com.koreait.mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//모든 컨트롤러라면, 적어도 아래의 명시한 메서드를 반드시 구현하도록, 강제시켜 보자!!
public interface Controller {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	//이 요청에 의해 보여줘야할 뷰이름..
	public String getViewName();
	
	//이 요청을 포워딩해야 할지 여부 반환 
	public boolean isForward();
}
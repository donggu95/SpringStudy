package com.koreait.mvc.controller.movie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.mvc.controller.Controller;
import com.koreait.mvc.model.movie.MovieManager;

//3단계: 알맞는 로직 객체에 일시킨다 
//4단계: 뷰로 가져갈 것이 있다면 결과를 저장해둔다!!
public class MovieController implements Controller{
											/* MovieController is a Controller */
	private MovieManager movieManager;
	
	public MovieController() {
		movieManager = new MovieManager();
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String movie = request.getParameter("movie");
		
		//3단계: 일시킨다
		String msg=movieManager.getAdvice(movie);
		
		//4단계: 데이터 전달을 위한 저장 
		request.setAttribute("result", msg);
	}
	
	public String getViewName() {
		return "/result/movie";
	}	
}

















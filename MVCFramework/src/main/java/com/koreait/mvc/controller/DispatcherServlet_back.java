package com.koreait.mvc.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*우리는 현재 MVC패턴을 적용한 프로그램을 만드는데 성공은 했다..
 * 하지만, 모든 클라이언트의 매 요청마다 1:1 대응하는 매핑을 걸면, 유지보수성이 오히려 떨어지므로
 * 대기업 시스템의 콜센터 처럼, 요청의 진입 부에 전담 컨트롤러를 두고, 모든 요청을 이 하나의 컨트롤러에서
 * 처리해서, 하위 컨트롤러에 전달해보자!! 
 * 
 * 1) 요청받는다 (형님)
 * 2) 요청을 분석한다 ( 적절한 전문 컨트롤러에게 분담(전달)시켜주기 위해) (형님)
 * 3) 알맞는객체 (비즈니스 로직 객체==모델)에 일 시킨다 (동생)
 * 4) 뷰로 가져갈것이 있다면 결과를 저장한다 (동생)
 * 5) 결과를 보여준다
 * 
 * */
public class DispatcherServlet_back extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	//Get 되었던 Post가 되었던 모든 요청을 아래의 메서드에서 공통으로 처리해보자!!
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("클라이언트의 요청을 받음");
		
		//2) 요청을 분석하여 알맞는 컨트롤러에게 요청 전달(동생선택)
		//들어온 요청이 혈액형인지? 영화인지? 글쓰기 인지?...
		
		//전체 url http://localhost:8090   /movie.do
		String uri=request.getRequestURI();
		System.out.print("클라이언트가 요청한 uri는 "+uri);
		
		//현재까지 프로그램이 돌아는 간다... MVC도 맞다.. 유지보수성에 실패..if문 블럭을 완전히 없애야 한다!!
		Controller controller=null;
		
		//실행시에 문자열로 지정한 클래스의 실제 클래스 코드를 얻는 방법
		try {
			Class obj=Class.forName("com.koreait.mvc.controller.blood.BloodController");
			controller = (Controller)obj.newInstance();
			controller.doPost(request, response); //여기서의 호출은 자식객체의 오버라이드 된 메서드 호출
			RequestDispatcher dis=request.getRequestDispatcher("/blood/result.jsp"); //포워딩 객체
			dis.forward(request, response);//포워딩시 request, response 매개변수로 사용하는 이유..
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		/*
		if(uri.equals("/blood.do")) {
			//메모리에 인스턴스를 생성하고 싶은 대상 클래스명 및 경로를 이용하여, 
			//해당 클래스의 Class형을 반환하는 메서드를 사용해서 동적으로 컨트롤러의 인스턴스를 생성해보자!!
			//5) 결과를 보여준다.
			
			
		}else if(uri.equals("/movie.do")) {
			
			//5) 결과를 보여준다.
			
		}
		*/
		
	}
	
}










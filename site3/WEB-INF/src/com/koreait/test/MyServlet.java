//자바클래스 중 오직 웹서버에서 해석 및 실행될 수 있는 클래스를 가리켜 서블릿이라 한다
//javaEE API에 소속
package com.koreait.test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.io.IOException;

public class  MyServlet extends HttpServlet{
	//클라이언트가 GET방식으로 요청을 시도하면 동작할 메서드 재정의!!!
	//즉, 메서드명을 우리가 정하는 것이 아니라, sun의 javaEE api 중 서블릿이라는 클래스의
	//메서드를 재정의 해야한다!!
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//클라이언트의 웹브라우저에 출력될 응답정보를 생성하자
		response.serContentType("text/html");
		response.setCharacterEncoding("utf-8");

		PrintWriter out=response.getWriter();
		out.print("my first servlet !!");
		out.print("<button>나버튼</button>");
		out.print("<button>나버튼</button>");
	}//클라이언트의 요청처리를 완료

	public void destroy(){
	}
}


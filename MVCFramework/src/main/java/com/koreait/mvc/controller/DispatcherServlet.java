package com.koreait.mvc.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*우리는 현재 MVC패턴을 적용한 프로그램을만드는데 성공은 했다..
 *하지만, 모든 클라이언트의 매 요청마다 1:1 대응하는 매핑을 걸면, 유지보수성이 오히려 떨어지므로
 *대기업 시스템의 콜센터 처럼, 요청의 진입 부에 전담 컨트롤러를 두고, 모든 요청을 이 하나의 컨트롤러에서 
 *처리해서, 하위 컨트롤러에 전달해보자!! 
 *
 *1) 요청받는다 (형님)
 *2) 요청을 분석한다 ( 적절한 전문 컨트롤러에게 분담(전달)시켜주기 위해) (형님)
 *3) 알맞는 객체(비즈니스 로직 객체==모델)에 일 시킨다 (동생)
 *4) 뷰로 가져갈것이 있다면 결과를 저장한다(동생)
 *5) 결과를 보여준다
 *
 * */
public class DispatcherServlet extends HttpServlet{
	FileInputStream fis; //컨트롤러 등의 매핑정보가 들어있는 파일을 읽기위한 스트림
	Properties props;
	
	//이 서블릿의 인스턴스가 생성되고 난 직후, 톰켓에 의해 서블릿 초기화가 발생하여, 이때 호출되는 메서드가 바로  init() 자격증부여의 타이밍..
	public void init(ServletConfig config) throws ServletException {
		//ServletConfig : 서블릿으로서 알아야할 어플리케이션의 환경정보..
		//ServletContext 가 application내장객체의 원형이다
		//application내장객채: 어플리케이션의 전역적 정보를 가진객체, 서버가동시 태어나서 생명을 같이함.. 
		//생명력 유지 순서 :  application > session > request,response
		String mappingPath=config.getInitParameter("mappingPath");
		ServletContext application=config.getServletContext();
		String realPath=application.getRealPath(mappingPath);
		
		try {
			fis=new FileInputStream(realPath);
			System.out.print(realPath);
			props = new Properties(); //자바의 컬렉션 프레임웍의맵의 자식인 프로퍼티스는 key=value의 쌍으로
			//된 데이터만을 이해할수 잇는 객체 ..
			props.load(fis); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	//Get 되었던 Post가 되었던 모든 요청을 아래의 메서드에서 공통으로 처리해보자!!
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		/*-------------------------------------------------------------
		 요청을 분석한다 if문으로 하지말자 ( 유지보수성이 떨어지니까)
		-------------------------------------------------------------*/
		String uri=request.getRequestURI();
		// http://localhost:9999    /blood.do
		System.out.print("클라이언트가 요청한 uri는 "+uri); 
		Controller controller=null;
		
		try {
			String controllerName=props.getProperty(uri);
			System.out.println("현재 uri인 "+uri+"의 요청을 수행할 컨트롤러는 "+controllerName);
			
			Class obj=Class.forName(controllerName); //문자열로 클래스의 경로를 대입하면, 실제 해당 클래스에 대한 정보를 Class 클래스로
			//반환해주는 메서드가 바로 Class.forName() 
			controller = (Controller)obj.newInstance(); //인스턴스 생성 new 연산자 없이!!! 
			controller.doPost(request, response); //여기서의 호출은 자식객체의 오버라이드 된 메서드 호출
			// 3, 4단계 동생이 수행!!
			
			//형님은 스스로 검색하려 하지말고, 동생에게 물어보면 된다..
			String viewName=controller.getViewName(); //뷰찾기!
			System.out.println("viewName is "+viewName);  //    /result/blood  , /result/movie
			
			String viewPage = props.getProperty(viewName); //형님이 다시한번 검색을 통해 실제 jsp페이지를 검색들어가자!!
			// /notice/list.do
			
			//개발을 하다보면 요청을유지할때가 있고(요청유지), 유지할 필요가 없을때가 있다..(요청끊기)
			if(controller.isForward()) {
				RequestDispatcher dis=request.getRequestDispatcher(viewPage); //포워딩 객체
				dis.forward(request, response);//포워딩시 request, response 매개변수로 사용하는 이유..
			}else {
				//리다이렉트 (클라인언트를 몽유병 환자로...) 지정한 URL로 재접속을 유도!!! 
				response.sendRedirect(viewPage);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	//이 서블릿이 소멸할때 호출되는 메서드 
	public void destroy() {
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
}














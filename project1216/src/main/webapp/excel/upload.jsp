<%@page import="org.apache.poi.hssf.usermodel.HSSFWorkbook"%>
<%@page import="org.apache.poi.util.SystemOutLogger"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	// 클라이언트가 전송한 파일 데이터를 서버에 저장한 후, 그 엑셀파일을 읽어들여 다시 mysql에 넣자 !
	// request.getParameter(""); 는 파라미터가 텍스트인 경우에만 가능 !
	
	// 업로드 관련 설정 (용량제한, 저장위치 등등 )
	DiskFileItemFactory factory = new DiskFileItemFactory();
	// jsp 내장객체 request, response, session, out, error .... application( ServletContext라 웹 어플리케이션의 자체에 대한 정보)
	String realPath = application.getRealPath("/excel/data"); // mac, window 등에서 너의 위치 !
	out.print("당신이 요청한 엑셀의 디렉토리의 실제 물리적 위치는?" + realPath+"<br>");
	File file = new File(realPath);
	
	factory.setRepository(file); // 업로드한 파일이 저장될 위치
	
	factory.setSizeThreshold(1*1024*1024);  // 1메가로 제한 
	
	// 위에서 업로드 설정을 끝냈으므로 지금부터는 업로드 시작 !! ServletFileUpload
	ServletFileUpload upload = new ServletFileUpload(factory); // 업로드할 파일의 용량, 경로 인식.
	
	//업로드 시점은 ? 클라이언트가 전송한 요청에 대한 분석을 시도할 때 
	List<FileItem> itemList = upload.parseRequest(request);
	
	out.print(itemList.size()+"<br>");
	
	for( FileItem item : itemList ) {
		if (item.isFormField() == false) { // 파일 컴포넌트임 
			out.print(item.getName()+"<br>");
			// 새로운 File 클래스 인스턴스 생성 
			File uploadFile = new File(realPath + "/" + item.getName()); 
			item.write(uploadFile); // 물리적 저장  디렉토리명 + .파일명.확장자  
			
		}
	}
	
	// 2단계 : 서버에 저장된 엑셀파일을 해석해서 db에 넣기 
	HSSFWorkbook workbook = new HSSFWorkbook();;
	
%>
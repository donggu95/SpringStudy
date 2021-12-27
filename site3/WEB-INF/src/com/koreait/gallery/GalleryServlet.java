package com.koreait.gallery;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileUploadException;
import java.util.List;
import org.apache.commons.fileupload.FileItem;

public class GalleryServlet extends HttpServlet{
	DiskFileItemFactory factory;

	//클라이언트의 요청을 처리할 메서드를 재정의
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");// 스트림 사용 전에 미리 해야함
		PrintWriter out = response.getWriter(); //응답 정보 객체로 부터 출력스트림 얻기!!	
		
		//클라이언트가 전송한 파라미터를 받아서, 업로드를 구현..
		
		//파라미터 받기!!
		request.setCharacterEncoding("utf-8"); //파리머터에 대한 인코딩 처리(다국어 문자가 깨지지 않도록)

		//아파치 파일 업로드를 이용하여 업로드 구현
		factory = new DiskFileItemFactory();//업로드 설정 객체 (파일용량제한, 저장경로..)
		String saveDir="C:/jsp_workspace/site3/data";
		File file=new File(saveDir);//일반클래스니깐 new해야함
		factory.setRepository(file);//저장경로지정
		factory.setSizeThreshold(1*1024*1024);//파일의 용량제한
		
		try{
			ServletFileUpload fileUpload=new ServletFileUpload(factory); //업로드를 처리하는 핵심객체!!
			//업로드 처리!!
			List<FileItem> itemList=fileUpload.parseRequest(request); //multipart 한 복합형태의 전송데이터를 분석....
			out.print("당신이 업로드한 컴포넌트들의 수는 " +  itemList.size()+"<br>");
			
			for(int i=0; i<itemList.size();i++){
				FileItem item=itemList.get(i);//하나씩 끄집어 내서 text필드이면 db처리, 파일이면 저장처리....
				
				if(item.isFormField()){//텍스트 폼이야!!
					//텍스트필드의 값 추출
					String title=item.getString("utf-8");
					out.print("제목은 "+title);

				}else{//아니면 파일컴포넌트임...
					out.print("업로드한 파일명은 "+item.getName());

					//C:/jsp_workspace/site3/data /파일명.jsp
					File saveFile=new File(saveDir+"/"+item.getName());
					item.write(saveFile);//물리적인 저장!!
					out.print("파일 저장 성공<br>");
				}
			}
		}catch(FileUploadException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
			


		//out.print("넘어온 제목 파라미터는 " + title + "<br>");
	}
}

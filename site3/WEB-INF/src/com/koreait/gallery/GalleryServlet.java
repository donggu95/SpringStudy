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

	//Ŭ���̾�Ʈ�� ��û�� ó���� �޼��带 ������
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		response.setContentType("text/html;charset=utf-8");// ��Ʈ�� ��� ���� �̸� �ؾ���
		PrintWriter out = response.getWriter(); //���� ���� ��ü�� ���� ��½�Ʈ�� ���!!	
		
		//Ŭ���̾�Ʈ�� ������ �Ķ���͸� �޾Ƽ�, ���ε带 ����..
		
		//�Ķ���� �ޱ�!!
		request.setCharacterEncoding("utf-8"); //�ĸ����Ϳ� ���� ���ڵ� ó��(�ٱ��� ���ڰ� ������ �ʵ���)

		//����ġ ���� ���ε带 �̿��Ͽ� ���ε� ����
		factory = new DiskFileItemFactory();//���ε� ���� ��ü (���Ͽ뷮����, ������..)
		String saveDir="C:/jsp_workspace/site3/data";
		File file=new File(saveDir);//�Ϲ�Ŭ�����ϱ� new�ؾ���
		factory.setRepository(file);//����������
		factory.setSizeThreshold(1*1024*1024);//������ �뷮����
		
		try{
			ServletFileUpload fileUpload=new ServletFileUpload(factory); //���ε带 ó���ϴ� �ٽɰ�ü!!
			//���ε� ó��!!
			List<FileItem> itemList=fileUpload.parseRequest(request); //multipart �� ���������� ���۵����͸� �м�....
			out.print("����� ���ε��� ������Ʈ���� ���� " +  itemList.size()+"<br>");
			
			for(int i=0; i<itemList.size();i++){
				FileItem item=itemList.get(i);//�ϳ��� ������ ���� text�ʵ��̸� dbó��, �����̸� ����ó��....
				
				if(item.isFormField()){//�ؽ�Ʈ ���̾�!!
					//�ؽ�Ʈ�ʵ��� �� ����
					String title=item.getString("utf-8");
					out.print("������ "+title);

				}else{//�ƴϸ� ����������Ʈ��...
					out.print("���ε��� ���ϸ��� "+item.getName());

					//C:/jsp_workspace/site3/data /���ϸ�.jsp
					File saveFile=new File(saveDir+"/"+item.getName());
					item.write(saveFile);//�������� ����!!
					out.print("���� ���� ����<br>");
				}
			}
		}catch(FileUploadException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
			


		//out.print("�Ѿ�� ���� �Ķ���ʹ� " + title + "<br>");
	}
}

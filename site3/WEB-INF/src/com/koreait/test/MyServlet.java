//�ڹ�Ŭ���� �� ���� ���������� �ؼ� �� ����� �� �ִ� Ŭ������ ������ �����̶� �Ѵ�
//javaEE API�� �Ҽ�
package com.koreait.test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import java.io.IOException;

public class  MyServlet extends HttpServlet{
	//Ŭ���̾�Ʈ�� GET������� ��û�� �õ��ϸ� ������ �޼��� ������!!!
	//��, �޼������ �츮�� ���ϴ� ���� �ƴ϶�, sun�� javaEE api �� �����̶�� Ŭ������
	//�޼��带 ������ �ؾ��Ѵ�!!
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Ŭ���̾�Ʈ�� ���������� ��µ� ���������� ��������
		response.serContentType("text/html");
		response.setCharacterEncoding("utf-8");

		PrintWriter out=response.getWriter();
		out.print("my first servlet !!");
		out.print("<button>����ư</button>");
		out.print("<button>����ư</button>");
	}//Ŭ���̾�Ʈ�� ��ûó���� �Ϸ�

	public void destroy(){
	}
}


/*�� Ŭ������ ���� ����
	- ���� ���Ǵ� �ڵ带 �����ϱ� ���� ����
*/
package com.koreait.util;

public class  FileManager{
	
	//Ȯ���ڸ� �����ϴ� �޼�������
	public String getExt(String path){

		int lastIndex = path.lastIndexOf(".");//Ȯ���� ������ ������ ���� ��ġ

		System.out.print("������ ���� �ε����� " +lastIndex+"<br>");

		String ext=path.substring(lastIndex+1, path.length());
		System.out.print("������ Ȯ���ڴ� "+ext+"<br>");

		return ext;	
	}
}
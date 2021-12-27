/*이 클래스의 정의 목적
	- 자주 사용되는 코드를 재사용하기 위해 정의
*/
package com.koreait.util;

public class  FileManager{
	
	//확장자만 추출하는 메서드정의
	public String getExt(String path){

		int lastIndex = path.lastIndexOf(".");//확장자 직전의 마지막 점의 위치

		System.out.print("마지막 점의 인덱스는 " +lastIndex+"<br>");

		String ext=path.substring(lastIndex+1, path.length());
		System.out.print("구해진 확장자는 "+ext+"<br>");

		return ext;	
	}
}
package com.koreait.mvc.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropTest {
	FileInputStream fis;
	Properties props;
	
	public PropTest() {
		try {
			fis = new FileInputStream("C:/jsp_workspace/MVCFramework/src/main/webapp/WEB-INF/memo.babo\r\n"
					+ "");
			props = new Properties();
			System.out.println(props);
			props.load(fis); // 스트림 탑재!!
			
			String value=props.getProperty("zerg");
			System.out.println("이 요청을 처리할 동생 컨트롤러는 "+value);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis!=null) {
				try {
					fis.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new PropTest();
		
	}

}

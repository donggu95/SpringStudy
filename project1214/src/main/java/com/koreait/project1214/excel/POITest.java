package com.koreait.project1214.excel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//마이크로소프트 오피스 제품을 제어할수 있는 무료 라이브러리인 아파치의 POI를 이용해본다~!
public class POITest {
	File file;
	
	public POITest() {
		URL url=ClassLoader.getSystemResource("com/koreait/project1214/excel/data.xlsx");
		try {
			file = new File(url.toURI());
			XSSFWorkbook workbook=new XSSFWorkbook(file); //엑셀파일
			System.out.println("파일 접근 성공");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	
	public static void main(String[] args) {
		new POITest();
	}

}

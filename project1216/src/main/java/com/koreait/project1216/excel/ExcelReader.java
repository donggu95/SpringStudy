package com.koreait.project1216.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.util.SystemOutLogger;

public class ExcelReader {
	FileInputStream fis;
	
	public ExcelReader() {
		//파일의 경로를 클래스패스로부터 구하자!!
		try {
			URL url=ClassLoader.getSystemResource("com/koreait/project1216/excel/data2.xls");
			URI uri = url.toURI();
			File file = new File(uri);
			fis = new FileInputStream(file);
			HSSFWorkbook workbook=new HSSFWorkbook(fis);//엑셀파일에 접근!!
			HSSFSheet sheet=workbook.getSheetAt(0); //첫번째 시트 접근!!!
			
			int totalRecord = sheet.getLastRowNum();
			
			for(int i=0; i<5; i++) {
				HSSFRow row=sheet.getRow(i);
	
				Iterator it=row.iterator();//cell들을 나열해놓은 막대기!!
				
				while( it.hasNext()) {//요소가 발견될때까지...
					HSSFCell cell = (HSSFCell)it.next(); //rs처럼 호출시마다 전진하면서 다음 요소를 접근하여 오브젝트를 반환한다..
					if(cell.getCellType() == CellType.STRING) {
						System.out.print(cell.getStringCellValue()+"\t");
					}else if(cell.getCellType() == CellType.NUMERIC) {
						System.out.print(cell.getNumericCellValue()+"\t");
					}
				}
				System.out.println("");
			}
			//HSSFCell cell=row.getCell(0);
			//String category=cell.getStringCellValue();
			//System.out.print(category);
		
			
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
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
		new ExcelReader();
	}
}







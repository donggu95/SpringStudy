package com.koreait.project1210.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.plaf.DimensionUIResource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * 웹브라우저가 아닌, 자바 일반 클래스도 웹서버에 요청을 할 수 있을까? 당연!!
 * */
public class DataLoader extends JFrame{
   JButton bt;
   JTextArea area;
   URL url;
   URLConnection urlCon;
   HttpURLConnection httpCon; //웹 서버와의 통신이 가능한 객체 GET/Post
   
   InputStreamReader reader;//문자기반 입력스트림2
   BufferedReader buffr;//문자기반의 버퍼처리된 스트림 3
   
   public DataLoader() {
      bt = new JButton("Load");
      area = new JTextArea();
      
      // 조립
      setLayout(new java.awt.FlowLayout());
      add(bt);
      add(area);
      
      // 스타일적용
      area.setPreferredSize(new java.awt.Dimension(470,320));
      
      bt.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            System.out.println("눌렀느야?");
            requestWeb();
         }
      });
      
      // 윈도우 설정
      this.setSize(500,400);
      this.setVisible(true);
   }
   public void requestWeb() {
      try {
		url = new URL("http://ip:220.76.42.103/map/list"); //웹상의 주소
		urlCon=url.openConnection();
		httpCon =(HttpURLConnection)urlCon;
		
		//요청을 시도해보자!!
		httpCon.setRequestMethod("GET");
		
		int responseCode=httpCon.getResponseCode();
		System.out.println("responseCode is" + responseCode);
		
		InputStream is = httpCon.getInputStream();//바이트 기반의 입력스트림...
		reader = new InputStreamReader(is); //문자기반 스트림으로 업그레이드!!(빨대 덧씌움) 2단계 업그레이드
		buffr = new BufferedReader(reader);//버퍼기반 스트림으로 업그레이드 3단계 업그레이드
		
		//문자열 모으기!!!
		StringBuffer data=new StringBuffer();//String class 아니다!!!
		
		while(true) {
			String msg=buffr.readLine();//한줄의 데이터
			if(msg==null)break;
			data.append(msg);
		}
		
		System.out.println(data.toString());
		//서버로부터 String으로 전달받은 데이터는 json객체도 아니고 그냥 String일 뿐이다..
		//따라서 객체처럼 .찍고 접근이 불가능하다..String --convert ---> Json Object 변환
		//여러 라이브러리 중 simple json 라이브러리를 활용해본다.
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject=(JSONObject)jsonParser.parse(data.toString());//문자열을 파싱....
		
		area.append("pk : "+jsonObject.get("map_id")+"\n");
		area.append("제목 : "+jsonObject.get("title")+"\n");
		area.append("위도 : "+jsonObject.get("lati")+"\n");
		area.append("경도 : "+jsonObject.get("longi")+"\n");
		area.append("파일명 : "+jsonObject.get("filename")+"\n");
		
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}catch(ParseException e){
		e.printStackTrace();
	}finally {
		if(buffr != null) {
			try {
				buffr.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
   }
   
   public static void main(String[] args) {
      new DataLoader();
   }
}
package com.koreait.project1210.swing;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.koreait.project1210.domain.Pet;

//해석 대상이 되는 즉 파싱 대상이 되는 xml의 각 노드(=node)를 만날때 마다
//적절한 이벤트를 발생시켜주는 객체가 바로 DefaultHandler이다!!
public class MyHandler extends DefaultHandler{
	ArrayList<Pet> list;
	//각 태그마다 조건을 부여해야 하므로, 실행부가 어느 위치에 와있는지를 알수있는 논리값을 이용해보자!!
	boolean isPet=false;
	boolean isName=false;
	boolean isType=false;
	boolean isAge=false;
	Pet pet;
	public void startDocument() throws SAXException {
		System.out.println("문서가 시작되었어요!!");
		list = new ArrayList<Pet>();
	}

	//시작태그노드를 발견했을때
	public void startElement(String uri, String localName, String tag, Attributes attributes) throws SAXException {
		System.out.print("<"+tag+">");
		
		//무조건 생성하지말고, 반려동물 1마리를 시작하는 태그를 만났을때만....
		if(tag.equals("pet")) {
			pet = new Pet();
		}
		if(tag.equals("name")) {
			isName=true;
		}else if(tag.equals("type")) {
			isType=true;
		}else if(tag.equals("age")) {
			isAge=true;
		}
		
	}
	
	//태그사이의 문자열 노드를 발견했을때
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data=new String(ch, start, length);
		System.out.print(data);
		
		//조건....
		if(isName) {
			pet.setName(data);//name값을 대입하자!!
		}else if(isType) {
			pet.setType(data);//type 값을 대입하자!!
		}else if(isAge) {
			pet.setAge(Integer.parseInt(data));//type 값을 대입하자!!
		}
		
		
	}
	
	public void endElement(String uri, String localName, String tag) throws SAXException {
		System.out.println("</"+tag+">");
		if(tag.equals("name")) {
			isName=false;
		}else if(tag.equals("type")) {
			isType=false;
		}else if(tag.equals("age")) {
			isAge=false;
		}else if(tag.equals("pet")) {
			//한마리의 반려동물이 완성된 시점!! DTO를 List에 채워넣자!!
			list.add(pet);
		}
	}
	
	public void endDocument() throws SAXException {
		System.out.println("문서의 끝에 도달했어요 총 담겨진 발려동물수는 " +list.size());
		for(Pet obj : list) {
			System.out.println(obj.getName()+", "+obj.getType()+","+obj.getAge());
		}
	}
}

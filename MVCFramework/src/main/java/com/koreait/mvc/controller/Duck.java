package com.koreait.mvc.controller;

public class Duck extends Bird{
	
	public void fly() {
		System.out.println("오리가 난다");
	}
	
	public static void main(String[] args) {
		Bird b= new Bird();
		b.fly(); //새역할
		
		Bird b2 = new Duck();
		b2.fly(); //오리역할
	}

}

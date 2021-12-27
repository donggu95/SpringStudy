package com.koreait.mvc1223.util;

//내가 정의하는 예외객체
public class MyArrayException extends RuntimeException{

	public MyArrayException(String msg) {
		super(msg);
	}

	public MyArrayException(Throwable e) {
		super(e);
	}
	
	public MyArrayException(String msg, Throwable e) {
		super(msg, e);
	}
	
}

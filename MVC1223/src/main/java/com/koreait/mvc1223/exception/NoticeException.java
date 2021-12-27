package com.koreait.mvc1223.exception;

public class NoticeException extends RuntimeException{
	
	public NoticeException(String msg) {
		super(msg);
	}
	public NoticeException(Throwable e) {
		super(e);
	}
	public NoticeException(String msg, Throwable e) {
		super(msg, e);
	}
	
}

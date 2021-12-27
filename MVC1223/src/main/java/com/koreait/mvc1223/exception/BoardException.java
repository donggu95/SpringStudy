package com.koreait.mvc1223.exception;

public class BoardException extends RuntimeException{
	
	public BoardException(String msg) {
		super(msg);
	}
	public BoardException(Throwable e) {
		super(e);
	}
	public BoardException(String msg, Throwable e) {
		super(msg, e);
	}
}

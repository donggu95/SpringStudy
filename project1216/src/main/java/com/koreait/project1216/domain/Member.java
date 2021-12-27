package com.koreait.project1216.domain;

public class Member {
	private int menber_id;
	private String id;
	private String pass;
	private String name;
	private String email;
	private String regdate;
	public int getMenber_id() {
		return menber_id;
	}
	public void setMenber_id(int menber_id) {
		this.menber_id = menber_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}

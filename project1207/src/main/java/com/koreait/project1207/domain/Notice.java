package com.koreait.project1207.domain;

//로직을 담기 위함이 아닌, 단순히 테이블의 레코드 한건을 담고,
//클라이언트에 전송한 폼 데이터를 담기 위한 용도의 객체
public class Notice {
	private int notice_id;
	private String writer;
	private String title;
	private String content;
	private String redate;
	private int hit;
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRedate() {
		return redate;
	}
	public void setRedate(String redate) {
		this.redate = redate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
	
}

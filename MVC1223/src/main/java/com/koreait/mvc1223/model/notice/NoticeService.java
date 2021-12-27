package com.koreait.mvc1223.model.notice;

import java.util.List;

import com.koreait.mvc1223.domain.Notice;

public interface NoticeService {

	public List selectAll(); //목록
	public Notice select(int notice_id);//한건조회
	public void insert(Notice notice);
	public void update(Notice notice);
	public void delete(int notice_id);
	
}
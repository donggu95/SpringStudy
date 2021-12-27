package com.koreait.mvc1223.model.notice;

import java.util.List;

import com.koreait.mvc1223.domain.Notice;

//하위의 모든 Notice관련 DAO들의 최상위 인터페이스 
public interface NoticeDAO {
	public List selectAll(); //목록
	public Notice select(int notice_id);//한건조회
	public void insert(Notice notice);
	public void update(Notice notice);
	public void delete(int notice_id);
	
}
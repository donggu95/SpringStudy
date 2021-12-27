package com.koreait.mvc1223.model.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.koreait.mvc1223.domain.Notice;
import com.koreait.mvc1223.exception.NoticeException;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	//적용기술이 여러개라서 자료형이 여러개라면, 개발자가 자료형을 선택해 줄 수 있다..
	@Qualifier("mybatisNoticeDAO")
	private NoticeDAO noticeDAO;
	
	@Override
	public List selectAll() {
		return noticeDAO.selectAll();
	}

	@Override
	public Notice select(int notice_id) {
		return noticeDAO.select(notice_id);
	}

	public void insert(Notice notice) throws NoticeException{
		noticeDAO.insert(notice);		
	}

	@Override
	public void update(Notice notice) throws NoticeException {
		noticeDAO.update(notice);
	}

	@Override
	public void delete(int notice_id) throws NoticeException {
		noticeDAO.delete(notice_id);
	}
}
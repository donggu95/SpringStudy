package com.koreait.mvc1223.model.notice;

import java.io.NotActiveException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.mvc1223.domain.Notice;
import com.koreait.mvc1223.exception.NoticeException;


//componet-scan 에 의해 메모리에 올릴 하나의 컴포넌트 대상이 된다!!
@Repository
public class MybatisNoticeDAO implements NoticeDAO{
	//1순위) 아이디, 2순위) 자료형
	@Autowired
	private SqlSessionTemplate sessionTemplate;

	public List selectAll() {
		return sessionTemplate.selectList("Notice.selectAll");
	}

	@Override
	public Notice select(int notice_id) {
		return sessionTemplate.selectOne("Notice.select", notice_id);
	}

	@Override
	public void insert(Notice notice) throws NoticeException{ //예외처리를 호출부에 전가..
		int result=sessionTemplate.insert("Notice.insert", notice);
		
		if(result==0) {
			throw new NoticeException("게시물 등록실패");
		}
	}

	@Override
	public void update(Notice notice) throws NoticeException{
		int result=sessionTemplate.update("Notice.update", notice);
		if(result==0) {
			throw new NoticeException("게시물 수정실패");
		}
	}

	@Override
	public void delete(int notice_id) throws NoticeException{
		int result=sessionTemplate.delete("Notice.delete", notice_id);
		if(result==0) {
			throw new NoticeException("게시물 삭제실패");
		}
	}
	
}
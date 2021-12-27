package com.koreait.project1216.member.repository;

import org.apache.ibatis.session.SqlSession;

import com.koreait.project1216.domain.Member;
import com.koreait.project1216.mybatis.ConfigManager;

public class MemberDAO {
	private ConfigManager configManager=ConfigManager.getInstance();
	
	public int insert(Member member) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result=sqlSession.insert("Member.insert", member);
		sqlSession.commit(); //DML
		configManager.closeSession(sqlSession);
		return result;
	}
	
	//로그인 처리 메서드 null이면 회원정보 불일치로 인한 로그인 실패
	public Member loginCheck(Member member) {
		Member obj=null;
		SqlSession sqlSession = configManager.getSqlSession();
		obj=sqlSession.selectOne("Member.loginCheck", member);
		configManager.closeSession(sqlSession);
		return obj;
	}
}













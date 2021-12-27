package com.koreait.project1214.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.koreait.project1214.domain.News;
import com.koreait.project1214.mybatis.ConfigManager;

public class MybatisNewsDAO {
	ConfigManager configManager = ConfigManager.getInstance();
	SqlSession sqlSession =null;
	
	// 글등록 
	public int insert(News news) {
		int result = 0;
		sqlSession = configManager.getSqlSession();

		// 직접 쿼리실행을 하지 않고, xml에 명시한 쿼리를 수행
		result = sqlSession.insert("News.insert",news);
		sqlSession.commit(); //DML
		configManager.closeSession(sqlSession);//반납
		return result;
	}
	
	//모든글 가져오기
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = configManager.getSqlSession();
		list=sqlSession.selectList("News.selectAll");
		configManager.closeSession(sqlSession);//반납
		return list;
	}
	
	//글한건 가져오기
	public News select(int news_id) {
		News news=null;
		SqlSession sqlSession = configManager.getSqlSession();
		news=sqlSession.selectOne("News.select", news_id);
		configManager.closeSession(sqlSession);//반납
		return news;
	}
	//글한건 수정하기
	public int update(News news) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result=sqlSession.update("News.update", news);
		sqlSession.commit(); //DML
		configManager.closeSession(sqlSession);
		return result;
	}
	
	//글한건 삭제하기
	public int delete(int news_id) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		
		//자식레코드 지우고 부모 지운다.
		result=sqlSession.delete("Comments.delete", news_id); //이 뉴스에 소속된 모든 자식 삭제 요청
		
		if(result>0) {
			result=sqlSession.delete("News.delete", news_id);
			sqlSession.commit(); //DML (update, delete, insert)
		}else {
			sqlSession.rollback();
		}
		configManager.closeSession(sqlSession);//반납
		return result;
	}
	
	
}






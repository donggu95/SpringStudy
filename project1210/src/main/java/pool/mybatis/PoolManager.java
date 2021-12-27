package pool.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// Mybatis에서 SQL문을 수행하는 객체는 SqlSession이고,
// SQLSession은 SqlSessionFactory로부터, 매 쓰레드(클라이언트의 요청 시 서버에서 생성되는)마다 사용되는 일회성
// 객체이므로, 아래의 PoolManger를 제작하여 쿼리문 수행시마다 SqlSession 객체를 반환해주는 클래스를 정의하자!!

public class PoolManager {
   private static PoolManager instance; // 객체를 오직 한 개만 생성하기 위해 static
   String resource = "com/koreait/mybatis/config.xml";
   SqlSessionFactory sqlSessionFactory = null;

   private PoolManager() {
      try {
         InputStream inputStream = Resources.getResourceAsStream(resource);
         // 데이터 베이스 접속 정보를 가진 팩토리 객체
         sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static PoolManager getInstance() {
      if(instance == null) {   // 만약 객체가 없는 경우에는 객체를 생성하자
         instance = new PoolManager();
      }
      return instance;
   }
   
   // SQLSession을 가져갈 수 있도록 하자! (쿼리문 실행)
   public SqlSession getSqlSession() {
      return sqlSessionFactory.openSession();
   }
   
   // 쿼리문 닫기
   public void closeSqlSession(SqlSession sqlSession) {
      sqlSession.close();
   }
   
}
package pool.jndi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PoolManager {
   private static PoolManager instance;
   InitialContext context; // 검색객체
   DataSource ds;

   private PoolManager() {
      
      // jndi를 이용하여 자원 얻어오기 !! DataSource 얻기
      try {
         context = new InitialContext();
         System.out.println(context);
         ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql"); // Object를 반환하기 때문에 형변환 필요
      } catch (NamingException e) {
         e.printStackTrace();
      }
   }

   public static PoolManager getInstance() {
      if (instance == null) {
         instance = new PoolManager();
      }
      return instance;
   }

   // 커넥션을 얻어온다
   public Connection getConnection() {
      Connection con = null;
      try {
         con = ds.getConnection(); // 풀로부터 Connection 객체를 하나 대여
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return con;
   }

   public void freeConnection(Connection con) {
      try {
         if (con != null)
            con.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void freeConnection(Connection con, PreparedStatement pstmt) {
      try {
         if (pstmt != null)
            pstmt.close();
         if (con != null)
            con.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

   public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
      try {
         if (rs != null)
            rs.close();
         if (pstmt != null)
            pstmt.close();
         if (con != null)
            con.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
package com.koreait.project1214.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// 이 객체가 DAO 대신, server.xml의 jndi를 검색해서, DataSource를 얻어온 후 
// Connection을 필요로 하는 DAO들에게 배분해줄 것임

public class JNDIPool {
   private static JNDIPool instance = new JNDIPool();
   InitialContext context; // JNDI 검색객체
   DataSource ds;          // ConnectionPool을 구현한 객체 (커넥션 보유). 따라서 이 객체로부터 얻어진 커넥션을 close()하면
                           // db와의 접속이 끊기는 것이 아니라 다시 Pool로 돌아온다.

   public JNDIPool() {
      try {
         context = new InitialContext();
         ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
      } catch (NamingException e) {
         e.printStackTrace();
      }
   }

   // Connection 을 반환 받아가도록
   public Connection getConnection() {
      Connection con = null;
      try {
         con = ds.getConnection();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return con;
   }

   // close()
   public void freeConnection(Connection con) {
      if (con != null) {
         try {
            con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }

   public void freeConnection(Connection con, PreparedStatement pstmt) {
      if (pstmt != null) {
         try {
            pstmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if (con != null) {
         try {
            con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }

   public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
      if (rs != null) {
         try {
            rs.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if (pstmt != null) {
         try {
            pstmt.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      if (con != null) {
         try {
            con.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }

   // 객체 반환
   public static JNDIPool getInstance() {
      return instance;
   }
}
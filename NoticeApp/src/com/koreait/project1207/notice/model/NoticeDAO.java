package com.koreait.project1207.notice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.koreait.project1207.domain.Notice;

/*
 오직 Notice에 대한 CRUD(Create(=insert), Read(=select),Update,Delete)만을 수행하는 객체를 가리켜, 
 어플리케이션 설계분야에서는  DAO(Data Access Object)
 DTO (Data Transfer Object) 또는  VO (Value Object) : 데이터를 담아서 전달만을 하기 위한 객체이다. 로직이 없다 !!
 */
public class NoticeDAO {
	
	/*
		1) 드라이버 로드
		2) 접속
		3) 쿼리문 실행
		4) 접속해제 
	 */
	public int insert(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3307/jsp";
			
			con = DriverManager.getConnection(url,"root","0000");

			String sql = "insert into notice(title,writer,content) values (?,?,?)"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getWriter());
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());
			
			result = pstmt.executeUpdate(); // 쿼리 실행
			
			System.out.println(" 등록결과 " + result);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt !=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con !=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
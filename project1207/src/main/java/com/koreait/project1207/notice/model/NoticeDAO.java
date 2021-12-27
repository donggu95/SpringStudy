package com.koreait.project1207.notice.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.project1207.domain.Notice;

import pool.PoolManager;

/*
 오직 Notice에 대한 CRUD(Create(=insert), Read(=select),Update,Delete)만을 수행하는 객체를 가리켜, 
 어플리케이션 설계분야에서는  DAO(Data Access Object)
 DTO (Data Transfer Object) 또는  VO (Value Object) : 데이터를 담아서 전달만을 하기 위한 객체이다. 로직이 없다 !!
 */
public class NoticeDAO {
	
	PoolManager pool;
	
	public NoticeDAO() {
		pool =PoolManager.getInstance();
	}
	
	// Create 
	public int insert(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = pool.getConnection(); // 대여 !! 

			String sql = "insert into notice(title,writer,content) values (?,?,?)"; 
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());
			
			result = pstmt.executeUpdate(); // 쿼리 실행
			
			System.out.println(" 등록결과 " + result);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return result;
	}
	
	// Read
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> list = null;
		
		try {
			con = pool.getConnection();  // 접속 -> pool에서 대여하자 !
			String sql ="select * from notice order by notice_id desc";
			pstmt=con.prepareStatement(sql); //쿼리객체 준비
			rs=pstmt.executeQuery(); //쿼리 수행 후 결과 레코드 받기 !!
			// rs가 닫히기 전에, rs의 역할을 수행 할 가장 근접한 기능을 보유한 객체로 대체해보자 !
			
			list = new ArrayList<Notice>(); // 배열과 거의 같다 ! 단, 차이점이라면 대상이 오직 객체이며, 크기가 동적으로 변경될 수 있다.
			
			while(rs.next()) { // 커서 한칸 이동 
				Notice notice = new Notice(); // 인스턴스 1개 생성 (하나의 레코드 담을 수 있음)
				
				notice.setNotice_id(rs.getInt("notice_id")); //pk담기 
				notice.setTitle(rs.getString("title")); // 제목 담기
				notice.setWriter(rs.getString("writer")); // 작성자 담기
				notice.setContent(rs.getString("content")); // 내용 담기
				notice.setRegdate(rs.getString("regdate")); // 작성일 담기
				notice.setHit(rs.getInt("hit")); // 조회수 담기
			
				list.add(notice); // 리스트에 게시물을 1건 담고 있는 dto 넣기 !
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	
	// 레코드 1건 가져오기 
	public Notice select(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null; // return 할꺼니깐 !!!! 
		
		try {
			con = pool.getConnection(); // 접속객체 얻기 !!
			
			String sql = "select * from notice where notice_id=?";
			pstmt = con.prepareStatement(sql); // 쿼리객체 생성
			pstmt.setInt(1, notice_id); // 바인드변수값 지정 
			rs = pstmt.executeQuery(); // 쿼리실행
			
			rs.next();
			// rs가 곧 죽기전에, 대체제를 고민해보자 !!
			notice = new Notice();  // 레코드 1건 담을 인스턴스
			notice.setNotice_id(rs.getInt("notice_id"));  // pk
			notice.setTitle(rs.getString("title"));	// 제목 
			notice.setWriter(rs.getString("writer"));	// 작성자 	
			notice.setContent(rs.getString("content"));	// 내용 	
			notice.setRegdate(rs.getString("regdate"));	// 작성일 
			notice.setHit(rs.getInt("hit"));	//조회수 
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}  finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return notice;
	}
	
	// 글 한건 수정하기
	public int update(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0; // 수행결과를 반환할 변수 
		
		try {
			con = pool.getConnection();
	
			String sql = "update notice set title=?, writer=?, content=? where notice_id=?";
			pstmt = con.prepareStatement(sql); 
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getWriter());
			pstmt.setString(3, notice.getContent());
			pstmt.setInt(4, notice.getNotice_id());
			result = pstmt.executeUpdate(); // 쿼리실행 
			
		} catch (Exception e) {
				e.printStackTrace();
		}  finally {
			pool.freeConnection(con, pstmt);
		}
		return result;
	}
	
	// 레코드 1건 삭제 처리
	public int delete(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = pool.getConnection();

			String sql = "delete from notice where notice_id=?";
			pstmt = con.prepareStatement(sql); // 쿼리객체 생성 
			pstmt.setInt(1, notice_id);
			
			result = pstmt.executeUpdate(); // 삭제처리 
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return result;
	}
}
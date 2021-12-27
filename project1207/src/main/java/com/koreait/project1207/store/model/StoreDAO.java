package com.koreait.project1207.store.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.project1207.domain.Store;
import pool.jndi.PoolManager;

//오직 store 테이블에 대한 CRUD만을 수행하는 로직 객체
public class StoreDAO {
	PoolManager pool=PoolManager.getInstance();

	//등록
	public void insert(Store store) {
		Connection con=pool.getConnection();//접속객체 대여
		PreparedStatement pstmt = null;
		
		String sql="insert into store(title, sido, score) values(?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, store.getTitle());
			pstmt.setString(2, store.getSido());
			pstmt.setInt(3, store.getScore());
			result=pstmt.executeUpdate();//쿼리실행
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,pstmt);
		}
		return result;
	}
	
	//모든 레코드 가져오기
	public List selectAll() {
		Connection con;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List list=new ArrayList();
		
		con=pool.getConnection();
		String sql="select * from store order by store_id asc";
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery(); //쿼리실행
			
			while(rs.next()) {
				Store store = new Store();//텅빈상태
				store.setStore_id(rs.getInt("store_id"));
				store.setTitle(rs.getString("title"));
				store.setSido(rs.getString("sido"));
				store.setScore(rs.getInt("score"));
				
				list.add(store);//완성된 1건의 레코드를 표현안 DTO를 List 에 담자
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return list;
	}
	
	//레코드 한건 가져오기
	public Store select(int store_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Store store=null;
		
		con=pool.getConnection();
		String sql="select * from store where store_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, store_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {//레코드가 있다
				store = new Store(); //비어있는 DTO 생성
				store.setStore_id(rs.getInt("store_id"));//pk
				store.setTitle(rs.getString("title"));//title
				store.setSido(rs.getString("sido"));//sido
				store.setScore(rs.getInt("score"));//score
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return store;
	}
	
	//1건 수정
	public void update(Store store) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=pool.getConnection();
		String sql="update store set title=?, sido=?, score=? where store_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, store.getTitle());
			pstmt.setString(2, store.getSido());
			pstmt.setInt(3, store.getScore());
			pstmt.setInt(4, store.getStore_id());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//한건삭제
	public int delete(int store_id) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=pool.getConnection();
		String sql="delete from store where store_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, store_id);
			result=pstmt.executeUpdate();//쿼리 실행
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return result;
	}
}












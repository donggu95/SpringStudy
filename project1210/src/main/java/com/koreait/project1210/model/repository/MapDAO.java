package com.koreait.project1210.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.koreait.project1210.domain.Map;

import pool.jndi.PoolManager;

public class MapDAO {
	private PoolManager pool = PoolManager.getInstance();

	// 등록 
	public int insert(Map map) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con=pool.getConnection();
		String sql = "insert into map(title, lati, longi, filename) values(?,?,?,?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, map.getTitle());
			pstmt.setDouble(2, map.getLati());
			pstmt.setDouble(3, map.getLongi());
			pstmt.setString(4, map.getFilename());
			result=pstmt.executeUpdate();
			
			// 방금 넣은 insert가 일으킨 최신의 pk 반환 받기 !
			pstmt=con.prepareStatement("select last_insert_id() as map_id");
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("map_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con,pstmt);
		}
		return result;
	}
	
	// 방금 들어간 레코드 한건 가져오기
	public Map select(int map_id) {
		Map map = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		con=pool.getConnection();
		String sql="select * from map where map_id=?"; 
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, map_id);
			rs=pstmt.executeQuery();

			if(rs.next()) {
				map = new Map();
				map.setMap_id(rs.getInt("map_id"));
				map.setTitle(rs.getString("title"));
				map.setLati(rs.getDouble("lati"));
				map.setLongi(rs.getDouble("longi"));
				map.setFilename(rs.getString("filename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,pstmt,rs);
		}
		
		return map;
	}
	
	// 모든 레코드 가져오기  -> mybatis로 해보자! !!
	public List selectAll() {
		List list = null;
		Connection con = null;
		PreparedStatement ptsmt = null;
		ResultSet rs = null;
		
		String sql = "select * from map";
		
		
		
		return list;
	}
}
package com.koreait.mvc.model.repository.emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.koreait.mvc.model.pool.PoolManager;

public class DeptDAO {

	private PoolManager pool=PoolManager.getInstance();
	//모든 부서 가져오기
	public List selectAll() {
		Connection con=pool.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		List list=new ArrayList();
		
		String sql="select * from dept";
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Dept dept = new Dept();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
				
				list.add(dept);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}

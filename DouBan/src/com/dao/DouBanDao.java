package com.dao;

import com.db.DBHelper;
import com.bean.*;

import java.util.*;
import java.sql.*;

public class DouBanDao {
	
	public List<DouBanBean> GetAllList(){
		String sql="select * from movie__1 order by shows desc";


		System.out.println(sql);
		
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<DouBanBean> list=new ArrayList<DouBanBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				DouBanBean cnbean=new DouBanBean();
				cnbean.setYear(rs.getInt("year"));
				cnbean.setNum(rs.getString("num"));
				cnbean.setMoivename(rs.getString("moviename"));
				cnbean.setStars(rs.getString("stars"));
				cnbean.setShows(rs.getString("shows"));
				list.add(cnbean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//≤‚ ‘
	public static void main(String[] args) {
		System.out.println("");
	}
}
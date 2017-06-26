package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class GDownDao {
	//获取所有列表
	public List<GDownBean> GetAllList(String strwhere,String strorder){
		String sql="select * from GDown";
		if(!(isInvalid(strwhere)))
		{
			sql+=" where "+strwhere;
		}
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
//		System.out.println(sql);
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<GDownBean> list=new ArrayList<GDownBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				GDownBean cnbean=new GDownBean();
				cnbean.setGDown_Id(rs.getInt("GDown_Id"));
				cnbean.setGDown_Name(rs.getString("GDown_Name"));
				cnbean.setGDown_Type(rs.getString("GDown_Type"));
				cnbean.setGDown_Unit(rs.getString("GDown_Unit"));
				cnbean.setGDown_Twarehouse(rs.getInt("GDown_Twarehouse"));
				cnbean.setGDown_Tbar(rs.getInt("GDown_Tbar"));
				cnbean.setGDown_Num(rs.getInt("GDown_Num"));
				cnbean.setGDown_Time(rs.getString("GDown_Time"));
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
	

	//获取指定ID的实体Bean
	public GDownBean GetAllBean(int id){
		String sql="select * from GDown where GDown_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		GDownBean cnbean=new GDownBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setGDown_Id(rs.getInt("GDown_Id"));
				cnbean.setGDown_Name(rs.getString("GDown_Name"));
				cnbean.setGDown_Type(rs.getString("GDown_Type"));
				cnbean.setGDown_Unit(rs.getString("GDown_Unit"));
				cnbean.setGDown_Twarehouse(rs.getInt("GDown_Twarehouse"));
				cnbean.setGDown_Tbar(rs.getInt("GDown_Tbar"));
				cnbean.setGDown_Num(rs.getInt("GDown_Num"));
				cnbean.setGDown_Time(rs.getString("GDown_Time"));
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
		return cnbean;
	}
	
	//添加
	public void Add(GDownBean cnbean){
		String sql="insert into GDown (";
		sql+="GDown_Id,GDown_Name,GDown_Type,GDown_Unit,GDown_Twarehouse,GDown_Tbar,GDown_Num,GDown_Time";
		sql+=") values(";
		sql+="'"+cnbean.getGDown_Id()+"','"+cnbean.getGDown_Name()+"','"+cnbean.getGDown_Type()+"','"+cnbean.getGDown_Unit()+
				"','"+cnbean.getGDown_Twarehouse()+"','"+cnbean.getGDown_Tbar()+"','"+cnbean.getGDown_Num()+
				"','"+cnbean.getGDown_Time()+"'";
		sql+=")";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			stat.executeUpdate(sql);
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
	}
	//修改
	public void Update(GDownBean cnbean){
		String sql="update GDown set ";
		sql+="GDown_Id='"+cnbean.getGDown_Id()+"',";
		sql+="GDown_Name='"+cnbean.getGDown_Name()+"',";
		sql+="GDown_Type='"+cnbean.getGDown_Type()+"',";
		sql+="GDown_Unit='"+cnbean.getGDown_Unit()+"',";
		sql+="GDown_Twarehouse='"+cnbean.getGDown_Twarehouse()+"',";
		sql+="GDown_Tbar='"+cnbean.getGDown_Tbar()+"',";
		sql+="GDown_Num='"+cnbean.getGDown_Num()+"',";
		sql+="GDown_Time='"+cnbean.getGDown_Time()+"'";
		
		sql+=" where GDown_Id='"+cnbean.getGDown_Id()+"'";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			stat.executeUpdate(sql);
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
	}
	//删除
	public void Delete(String strwhere){
		String sql="delete from GDown where ";
		sql+=strwhere;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			stat.executeUpdate(sql);
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
	}

	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println("");
	}
	
}

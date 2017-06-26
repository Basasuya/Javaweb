package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class GUpDao {
	//获取所有列表
	public List<GUpBean> GetAllList(String strwhere,String strorder){
		String sql="select * from GUp";
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
		List<GUpBean> list=new ArrayList<GUpBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				GUpBean cnbean=new GUpBean();
				cnbean.setGUp_Id(rs.getInt("GUp_Id"));
				cnbean.setGUp_Name(rs.getString("GUp_Name"));
				cnbean.setGUp_Type(rs.getString("GUp_Type"));
				cnbean.setGUp_Unit(rs.getString("GUp_Unit"));
				cnbean.setGUp_Twarehouse(rs.getInt("GUp_Twarehouse"));
				cnbean.setGUp_Tbar(rs.getInt("GUp_Tbar"));
				cnbean.setGUp_Num(rs.getInt("GUp_Num"));
				cnbean.setGUp_Time(rs.getString("GUp_Time"));
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
	public GUpBean GetAllBean(int id){
		String sql="select * from GUp where GUp_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		GUpBean cnbean=new GUpBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setGUp_Id(rs.getInt("GUp_Id"));
				cnbean.setGUp_Name(rs.getString("GUp_Name"));
				cnbean.setGUp_Type(rs.getString("GUp_Type"));
				cnbean.setGUp_Unit(rs.getString("GUp_Unit"));
				cnbean.setGUp_Twarehouse(rs.getInt("GUp_Twarehouse"));
				cnbean.setGUp_Tbar(rs.getInt("GUp_Tbar"));
				cnbean.setGUp_Num(rs.getInt("GUp_Num"));
				cnbean.setGUp_Time(rs.getString("GUp_Time"));
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
	public void Add(GUpBean cnbean){
		String sql="insert into GUp (";
		sql+="GUp_Id,GUp_Name,GUp_Type,GUp_Unit,GUp_Twarehouse,GUp_Tbar,GUp_Num,GUp_Time";
		sql+=") values(";
		sql+="'"+cnbean.getGUp_Id()+"','"+cnbean.getGUp_Name()+"','"+cnbean.getGUp_Type()+"','"+cnbean.getGUp_Unit()+
				"','"+cnbean.getGUp_Twarehouse()+"','"+cnbean.getGUp_Tbar()+"','"+cnbean.getGUp_Num()+
				"','"+cnbean.getGUp_Time()+"'";
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
	public void Update(GUpBean cnbean){
		String sql="update GUp set ";
		sql+="GUp_Id='"+cnbean.getGUp_Id()+"',";
		sql+="GUp_Name='"+cnbean.getGUp_Name()+"',";
		sql+="GUp_Type='"+cnbean.getGUp_Type()+"',";
		sql+="GUp_Unit='"+cnbean.getGUp_Unit()+"',";
		sql+="GUp_Twarehouse='"+cnbean.getGUp_Twarehouse()+"',";
		sql+="GUp_Tbar='"+cnbean.getGUp_Tbar()+"',";
		sql+="GUp_Num='"+cnbean.getGUp_Num()+"',";
		sql+="GUp_Time='"+cnbean.getGUp_Time()+"'";
		
		sql+=" where GUp_Id='"+cnbean.getGUp_Id()+"'";
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
		String sql="delete from GUp where ";
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

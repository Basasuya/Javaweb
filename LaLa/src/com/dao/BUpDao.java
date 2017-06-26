package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class BUpDao {
	//获取所有列表
	public List<BUpBean> GetAllList(String strwhere,String strorder){
		String sql="select * from BUp";
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
		List<BUpBean> list=new ArrayList<BUpBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				BUpBean cnbean=new BUpBean();
				cnbean.setBUp_Id(rs.getInt("BUp_Id"));
				cnbean.setBUp_Name(rs.getString("BUp_Name"));
				cnbean.setBUp_Type(rs.getString("BUp_Type"));
				cnbean.setBUp_Unit(rs.getString("BUp_Unit"));
				cnbean.setBUp_Twarehouse(rs.getInt("BUp_Twarehouse"));
				cnbean.setBUp_Tbar(rs.getInt("BUp_Tbar"));
				cnbean.setBUp_Num(rs.getInt("BUp_Num"));
				cnbean.setBUp_Time(rs.getString("BUp_Time"));
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
	public BUpBean GetAllBean(int id){
		String sql="select * from BUp where BUp_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		BUpBean cnbean=new BUpBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setBUp_Id(rs.getInt("BUp_Id"));
				cnbean.setBUp_Name(rs.getString("BUp_Name"));
				cnbean.setBUp_Type(rs.getString("BUp_Type"));
				cnbean.setBUp_Unit(rs.getString("BUp_Unit"));
				cnbean.setBUp_Twarehouse(rs.getInt("BUp_Twarehouse"));
				cnbean.setBUp_Tbar(rs.getInt("BUp_Tbar"));
				cnbean.setBUp_Num(rs.getInt("BUp_Num"));
				cnbean.setBUp_Time(rs.getString("BUp_Time"));
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
	public void Add(BUpBean cnbean){
		String sql="insert into BUp (";
		sql+="BUp_Id,BUp_Name,BUp_Type,BUp_Unit,BUp_Twarehouse,BUp_Tbar,BUp_Num,BUp_Time";
		sql+=") values(";
		sql+="'"+cnbean.getBUp_Id()+"','"+cnbean.getBUp_Name()+"','"+cnbean.getBUp_Type()+"','"+cnbean.getBUp_Unit()+
				"','"+cnbean.getBUp_Twarehouse()+"','"+cnbean.getBUp_Tbar()+"','"+cnbean.getBUp_Num()+
				"','"+cnbean.getBUp_Time()+"'";
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
	public void Update(BUpBean cnbean){
		String sql="update BUp set ";
		sql+="BUp_Id='"+cnbean.getBUp_Id()+"',";
		sql+="BUp_Name='"+cnbean.getBUp_Name()+"',";
		sql+="BUp_Type='"+cnbean.getBUp_Type()+"',";
		sql+="BUp_Unit='"+cnbean.getBUp_Unit()+"',";
		sql+="BUp_Twarehouse='"+cnbean.getBUp_Twarehouse()+"',";
		sql+="BUp_Tbar='"+cnbean.getBUp_Tbar()+"',";
		sql+="BUp_Num='"+cnbean.getBUp_Num()+"',";
		sql+="BUp_Time='"+cnbean.getBUp_Time()+"'";
		
		sql+=" where BUp_Id='"+cnbean.getBUp_Id()+"'";
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
		String sql="delete from BUp where ";
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

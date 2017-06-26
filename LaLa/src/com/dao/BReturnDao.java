package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class BReturnDao {
	//获取所有列表
	public List<BReturnBean> GetAllList(String strwhere,String strorder){
		String sql="select * from BReturn";
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
		List<BReturnBean> list=new ArrayList<BReturnBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				BReturnBean cnbean=new BReturnBean();
				cnbean.setBReturn_Id(rs.getInt("BReturn_Id"));
				cnbean.setBReturn_Name(rs.getString("BReturn_Name"));
				cnbean.setBReturn_Type(rs.getString("BReturn_Type"));
				cnbean.setBReturn_Unit(rs.getString("BReturn_Unit"));
				cnbean.setBReturn_Cost(rs.getInt("BReturn_Cost"));
				cnbean.setBReturn_Pro(rs.getInt("BReturn_Pro"));
				cnbean.setBReturn_Num(rs.getInt("BReturn_Num"));
				cnbean.setBReturn_Time(rs.getString("BReturn_Time"));
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
	public BReturnBean GetAllBean(int id){
		String sql="select * from BReturn where BReturn_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		BReturnBean cnbean=new BReturnBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setBReturn_Id(rs.getInt("BReturn_Id"));
				cnbean.setBReturn_Name(rs.getString("BReturn_Name"));
				cnbean.setBReturn_Type(rs.getString("BReturn_Type"));
				cnbean.setBReturn_Unit(rs.getString("BReturn_Unit"));
				cnbean.setBReturn_Cost(rs.getInt("BReturn_Cost"));
				cnbean.setBReturn_Pro(rs.getInt("BReturn_Pro"));
				cnbean.setBReturn_Num(rs.getInt("BReturn_Num"));
				cnbean.setBReturn_Time(rs.getString("BReturn_Time"));
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
	public void Add(BReturnBean cnbean){
		String sql="insert into BReturn (";
		sql+="BReturn_Id,BReturn_Name,BReturn_Type,BReturn_Unit,BReturn_Cost,BReturn_Pro,BReturn_Num,BReturn_Time";
		sql+=") values(";
		sql+="null"+",'"+cnbean.getBReturn_Name()+"','"+cnbean.getBReturn_Type()+
				"','"+cnbean.getBReturn_Unit()+"','"+cnbean.getBReturn_Cost()+"','"+cnbean.getBReturn_Pro()+"','"+
				cnbean.getBReturn_Num()+
				"','"+cnbean.getBReturn_Time()+"'";
		sql+=")";
		System.out.println(sql);
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
		System.out.println("ok");
	}
	//修改
	public void Update(BReturnBean cnbean){
		String sql="update BReturn set ";
		sql+="BReturn_Id='"+cnbean.getBReturn_Id()+"',";
		sql+="BReturn_Name='"+cnbean.getBReturn_Name()+"',";
		sql+="BReturn_Type='"+cnbean.getBReturn_Type()+"',";
		sql+="BReturn_Unit='"+cnbean.getBReturn_Unit()+"',";
		sql+="BReturn_Cost='"+cnbean.getBReturn_Cost()+"',";
		sql+="BReturn_Pro='"+cnbean.getBReturn_Pro()+"',";
		sql+="BReturn_Num='"+cnbean.getBReturn_Num()+"',";
		sql+="BReturn_Time='"+cnbean.getBReturn_Time()+"'";
		
		sql+=" where BReturn_Id='"+cnbean.getBReturn_Id()+"'";
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
		String sql="delete from BReturn where ";
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

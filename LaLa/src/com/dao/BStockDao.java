package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class BStockDao {
	//获取所有列表
	public List<BStockBean> GetAllList(String strwhere,String strorder){
		String sql="select * from BStock";
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
		List<BStockBean> list=new ArrayList<BStockBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				BStockBean cnbean=new BStockBean();
				cnbean.setBStock_Id(rs.getInt("BStock_Id"));
				cnbean.setBStock_Name(rs.getString("BStock_Name"));
				cnbean.setBStock_Type(rs.getString("BStock_Type"));
				cnbean.setBStock_Unit(rs.getString("BStock_Unit"));
				cnbean.setBStock_Cost(rs.getInt("BStock_Cost"));
				cnbean.setBStock_Pro(rs.getInt("BStock_Pro"));
				cnbean.setBStock_Num(rs.getInt("BStock_Num"));
				cnbean.setBStock_Time(rs.getString("BStock_Time"));
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
	public BStockBean GetAllBean(int id){
		String sql="select * from BStock where BStock_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		BStockBean cnbean=new BStockBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setBStock_Id(rs.getInt("BStock_Id"));
				cnbean.setBStock_Name(rs.getString("BStock_Name"));
				cnbean.setBStock_Type(rs.getString("BStock_Type"));
				cnbean.setBStock_Unit(rs.getString("BStock_Unit"));
				cnbean.setBStock_Cost(rs.getInt("BStock_Cost"));
				cnbean.setBStock_Pro(rs.getInt("BStock_Pro"));
				cnbean.setBStock_Num(rs.getInt("BStock_Num"));
				cnbean.setBStock_Time(rs.getString("BStock_Time"));
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
	public void Add(BStockBean cnbean){
		String sql="insert into BStock (";
		sql+="BStock_Id,BStock_Name,BStock_Type,BStock_Unit,BStock_Cost,BStock_Pro,BStock_Num,BStock_Time";
		sql+=") values(";
		sql+="null"+",'"+cnbean.getBStock_Name()+"','"+cnbean.getBStock_Type()+
				"','"+cnbean.getBStock_Unit()+"','"+cnbean.getBStock_Cost()+"','"+cnbean.getBStock_Pro()+"','"+
				cnbean.getBStock_Num()+
				"','"+cnbean.getBStock_Time()+"'";
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
	public void Update(BStockBean cnbean){
		String sql="update BStock set ";
		sql+="BStock_Id='"+cnbean.getBStock_Id()+"',";
		sql+="BStock_Name='"+cnbean.getBStock_Name()+"',";
		sql+="BStock_Type='"+cnbean.getBStock_Type()+"',";
		sql+="BStock_Unit='"+cnbean.getBStock_Unit()+"',";
		sql+="BStock_Cost='"+cnbean.getBStock_Cost()+"',";
		sql+="BStock_Pro='"+cnbean.getBStock_Pro()+"',";
		sql+="BStock_Num='"+cnbean.getBStock_Num()+"',";
		sql+="BStock_Time='"+cnbean.getBStock_Time()+"'";
		
		sql+=" where BStock_Id='"+cnbean.getBStock_Id()+"'";
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
		String sql="delete from BStock where ";
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

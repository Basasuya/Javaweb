package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class GStockDao {
	//获取所有列表
	public List<GStockBean> GetAllList(String strwhere,String strorder){
		String sql="select * from GStock";
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
		List<GStockBean> list=new ArrayList<GStockBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				GStockBean cnbean=new GStockBean();
				cnbean.setGStock_Id(rs.getInt("GStock_Id"));
				cnbean.setGStock_Name(rs.getString("GStock_Name"));
				cnbean.setGStock_Type(rs.getString("GStock_Type"));
				cnbean.setGStock_Unit(rs.getString("GStock_Unit"));
				cnbean.setGStock_Cost(rs.getInt("GStock_Cost"));
				cnbean.setGStock_Pro(rs.getInt("GStock_Pro"));
				cnbean.setGStock_Num(rs.getInt("GStock_Num"));
				cnbean.setGStock_Time(rs.getString("GStock_Time"));
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
	public GStockBean GetAllBean(int id){
		String sql="select * from GStock where GStock_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		GStockBean cnbean=new GStockBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setGStock_Id(rs.getInt("GStock_Id"));
				cnbean.setGStock_Name(rs.getString("GStock_Name"));
				cnbean.setGStock_Type(rs.getString("GStock_Type"));
				cnbean.setGStock_Unit(rs.getString("GStock_Unit"));
				cnbean.setGStock_Cost(rs.getInt("GStock_Cost"));
				cnbean.setGStock_Pro(rs.getInt("GStock_Pro"));
				cnbean.setGStock_Num(rs.getInt("GStock_Num"));
				cnbean.setGStock_Time(rs.getString("GStock_Time"));
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
	public void Add(GStockBean cnbean){
		String sql="insert into GStock (";
		sql+="GStock_Id,GStock_Name,GStock_Type,GStock_Unit,GStock_Cost,GStock_Pro,GStock_Num,GStock_Time";
		sql+=") values(";
		sql+="null"+",'"+cnbean.getGStock_Name()+"','"+cnbean.getGStock_Type()+
				"','"+cnbean.getGStock_Unit()+"','"+cnbean.getGStock_Cost()+"','"+cnbean.getGStock_Pro()+"','"+
				cnbean.getGStock_Num()+
				"','"+cnbean.getGStock_Time()+"'";
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
	public void Update(GStockBean cnbean){
		String sql="update GStock set ";
		sql+="GStock_Id='"+cnbean.getGStock_Id()+"',";
		sql+="GStock_Name='"+cnbean.getGStock_Name()+"',";
		sql+="GStock_Type='"+cnbean.getGStock_Type()+"',";
		sql+="GStock_Unit='"+cnbean.getGStock_Unit()+"',";
		sql+="GStock_Cost='"+cnbean.getGStock_Cost()+"',";
		sql+="GStock_Pro='"+cnbean.getGStock_Pro()+"',";
		sql+="GStock_Num='"+cnbean.getGStock_Num()+"',";
		sql+="GStock_Time='"+cnbean.getGStock_Time()+"'";
		
		sql+=" where GStock_Id='"+cnbean.getGStock_Id()+"'";
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
		String sql="delete from GStock where ";
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

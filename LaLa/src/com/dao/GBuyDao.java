package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class GBuyDao {
	//获取所有列表
	public List<GBuyBean> GetAllList(String strwhere,String strorder){
		String sql="select * from GBuy";
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
		List<GBuyBean> list=new ArrayList<GBuyBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				GBuyBean cnbean=new GBuyBean();
				cnbean.setGBuy_Id(rs.getInt("GBuy_Id"));
				cnbean.setGBuy_Name(rs.getString("GBuy_Name"));
				cnbean.setGBuy_Type(rs.getString("GBuy_Type"));
				cnbean.setGBuy_Unit(rs.getString("GBuy_Unit"));
				cnbean.setGBuy_Pro(rs.getInt("GBuy_Pro"));
				cnbean.setGBuy_Num(rs.getInt("GBuy_Num"));
				cnbean.setGBuy_Time(rs.getString("GBuy_Time"));
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
	public GBuyBean GetAllBean(int id){
		String sql="select * from GBuy where GBuy_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		GBuyBean cnbean=new GBuyBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setGBuy_Id(rs.getInt("GBuy_Id"));
				cnbean.setGBuy_Name(rs.getString("GBuy_Name"));
				cnbean.setGBuy_Type(rs.getString("GBuy_Type"));
				cnbean.setGBuy_Unit(rs.getString("GBuy_Unit"));
				cnbean.setGBuy_Pro(rs.getInt("GBuy_Pro"));
				cnbean.setGBuy_Num(rs.getInt("GBuy_Num"));
				cnbean.setGBuy_Time(rs.getString("GBuy_Time"));
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
	public void Add(GBuyBean cnbean){
		String sql="insert into GBuy (";
		sql+="GBuy_Id,GBuy_Name,GBuy_Type,GBuy_Unit,GBuy_Pro,GBuy_Num,GBuy_Time";
		sql+=") values(";
		sql+="'"+cnbean.getGBuy_Id()+"','"+cnbean.getGBuy_Name()+"','"+cnbean.getGBuy_Type()+"','"+cnbean.getGBuy_Unit()+
				"','"+cnbean.getGBuy_Pro()+"','"+cnbean.getGBuy_Num()+
				"','"+cnbean.getGBuy_Time()+"'";
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
	public void Update(GBuyBean cnbean){
		String sql="update GBuy set ";
		sql+="GBuy_Id='"+cnbean.getGBuy_Id()+"',";
		sql+="GBuy_Name='"+cnbean.getGBuy_Name()+"',";
		sql+="GBuy_Type='"+cnbean.getGBuy_Type()+"',";
		sql+="GBuy_Unit='"+cnbean.getGBuy_Unit()+"',";
		sql+="GBuy_Pro='"+cnbean.getGBuy_Pro()+"',";
		sql+="GBuy_Num='"+cnbean.getGBuy_Num()+"',";
		sql+="GBuy_Time='"+cnbean.getGBuy_Time()+"'";
		
		sql+=" where GBuy_Id='"+cnbean.getGBuy_Id()+"'";
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
		String sql="delete from GBuy where ";
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

package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class BBuyDao {
	//获取所有列表
	public List<BBuyBean> GetAllList(String strwhere,String strorder){
		String sql="select * from BBuy";
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
		List<BBuyBean> list=new ArrayList<BBuyBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				BBuyBean cnbean=new BBuyBean();
				cnbean.setBBuy_Id(rs.getInt("BBuy_Id"));
				cnbean.setBBuy_Name(rs.getString("BBuy_Name"));
				cnbean.setBBuy_Type(rs.getString("BBuy_Type"));
				cnbean.setBBuy_Unit(rs.getString("BBuy_Unit"));
				cnbean.setBBuy_Pro(rs.getInt("BBuy_Pro"));
				cnbean.setBBuy_Num(rs.getInt("BBuy_Num"));
				cnbean.setBBuy_Time(rs.getString("BBuy_Time"));
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
	public BBuyBean GetAllBean(int id){
		String sql="select * from BBuy where BBuy_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		BBuyBean cnbean=new BBuyBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setBBuy_Id(rs.getInt("BBuy_Id"));
				cnbean.setBBuy_Name(rs.getString("BBuy_Name"));
				cnbean.setBBuy_Type(rs.getString("BBuy_Type"));
				cnbean.setBBuy_Unit(rs.getString("BBuy_Unit"));
				cnbean.setBBuy_Pro(rs.getInt("BBuy_Pro"));
				cnbean.setBBuy_Num(rs.getInt("BBuy_Num"));
				cnbean.setBBuy_Time(rs.getString("BBuy_Time"));
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
	public void Add(BBuyBean cnbean){
		String sql="insert into BBuy (";
		sql+="BBuy_Id,BBuy_Name,BBuy_Type,BBuy_Unit,BBuy_Pro,BBuy_Num,BBuy_Time";
		sql+=") values(";
		sql+="'"+cnbean.getBBuy_Id()+"','"+cnbean.getBBuy_Name()+"','"+cnbean.getBBuy_Type()+"','"+cnbean.getBBuy_Unit()+
				"','"+cnbean.getBBuy_Pro()+"','"+cnbean.getBBuy_Num()+
				"','"+cnbean.getBBuy_Time()+"'";
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
	public void Update(BBuyBean cnbean){
		String sql="update BBuy set ";
		sql+="BBuy_Id='"+cnbean.getBBuy_Id()+"',";
		sql+="BBuy_Name='"+cnbean.getBBuy_Name()+"',";
		sql+="BBuy_Type='"+cnbean.getBBuy_Type()+"',";
		sql+="BBuy_Unit='"+cnbean.getBBuy_Unit()+"',";
		sql+="BBuy_Pro='"+cnbean.getBBuy_Pro()+"',";
		sql+="BBuy_Num='"+cnbean.getBBuy_Num()+"',";
		sql+="BBuy_Time='"+cnbean.getBBuy_Time()+"'";
		
		sql+=" where BBuy_Id='"+cnbean.getBBuy_Id()+"'";
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
		String sql="delete from BBuy where ";
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

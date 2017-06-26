package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class BoodsDao {
	//获取所有列表
	public List<BoodsBean> GetAllList(String strwhere,String strorder){
		String sql="select * from Boods";
		if(!(isInvalid(strwhere)))
		{
			sql+=" where "+strwhere;
		}
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
		
	//	System.out.println("before the search");
	//	System.out.println(sql);
		
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<BoodsBean> list=new ArrayList<BoodsBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				BoodsBean cnbean=new BoodsBean();
				cnbean.setBoods_Id(rs.getInt("Boods_Id"));
				cnbean.setBoods_Name(rs.getString("Boods_Name"));
				cnbean.setBoods_Type(rs.getString("Boods_Type"));
				cnbean.setBoods_Unit(rs.getString("Boods_Unit"));
				cnbean.setBoods_Price(rs.getInt("Boods_Price"));
				cnbean.setBoods_Warehouse(rs.getInt("Boods_Warehouse"));
				cnbean.setBoods_Bar(rs.getInt("Boods_Bar"));
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
	
	public BoodsBean GetAllFirstBean(String strwhere){
		String sql="select * from Boods where "+strwhere;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		BoodsBean cnbean=new BoodsBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()){
				cnbean.setBoods_Id(rs.getInt("Boods_Id"));
				cnbean.setBoods_Name(rs.getString("Boods_Name"));
				cnbean.setBoods_Type(rs.getString("Boods_Type"));
				cnbean.setBoods_Unit(rs.getString("Boods_Unit"));
				cnbean.setBoods_Price(rs.getInt("Boods_Price"));
				cnbean.setBoods_Warehouse(rs.getInt("Boods_Warehouse"));
				cnbean.setBoods_Bar(rs.getInt("Boods_Bar"));
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
	
	//获取指定ID的实体Bean
	public BoodsBean GetAllBean(int id){
		String sql="select * from Boods where Boods_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		BoodsBean cnbean=new BoodsBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setBoods_Id(rs.getInt("Boods_Id"));
				cnbean.setBoods_Name(rs.getString("Boods_Name"));
				cnbean.setBoods_Type(rs.getString("Boods_Type"));
				cnbean.setBoods_Unit(rs.getString("Boods_Unit"));
				cnbean.setBoods_Price(rs.getInt("Boods_Price"));
				cnbean.setBoods_Warehouse(rs.getInt("Boods_Warehouse"));
				cnbean.setBoods_Bar(rs.getInt("Boods_Bar"));
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
	public void Add(BoodsBean cnbean){
		String sql="insert into Boods (";
		sql+="Boods_Id,Boods_Name,Boods_Type,Boods_Unit,Boods_Price,Boods_Warehouse,Boods_Bar";
		sql+=") values(";
		sql+="null" +",'"+cnbean.getBoods_Name()+"','"+cnbean.getBoods_Type()+"','"
				+cnbean.getBoods_Unit()+"','"+cnbean.getBoods_Price()+
				"','"+cnbean.getBoods_Warehouse()+"','"+cnbean.getBoods_Bar()+"'";
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
	public void Update(BoodsBean cnbean){
		String sql="update Boods set ";
		sql+="Boods_Id='"+cnbean.getBoods_Id()+"',";
		sql+="Boods_Name='"+cnbean.getBoods_Name()+"',";
		sql+="Boods_Type='"+cnbean.getBoods_Type()+"',";
		sql+="Boods_Unit='"+cnbean.getBoods_Unit()+"',";
		sql+="Boods_Price='"+cnbean.getBoods_Price()+"',";
		sql+="Boods_Warehouse='"+cnbean.getBoods_Warehouse()+"',";
		sql+="Boods_Bar='"+cnbean.getBoods_Bar()+"'";
		
		sql+=" where Boods_Id='"+cnbean.getBoods_Id()+"'";
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
		String sql="delete from Boods where ";
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

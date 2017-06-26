package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class BDownDao {
	//获取所有列表
	public List<BDownBean> GetAllList(String strwhere,String strorder){
		String sql="select * from BDown";
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
		List<BDownBean> list=new ArrayList<BDownBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				BDownBean cnbean=new BDownBean();
				cnbean.setBDown_Id(rs.getInt("BDown_Id"));
				cnbean.setBDown_Name(rs.getString("BDown_Name"));
				cnbean.setBDown_Type(rs.getString("BDown_Type"));
				cnbean.setBDown_Unit(rs.getString("BDown_Unit"));
				cnbean.setBDown_Twarehouse(rs.getInt("BDown_Twarehouse"));
				cnbean.setBDown_Tbar(rs.getInt("BDown_Tbar"));
				cnbean.setBDown_Num(rs.getInt("BDown_Num"));
				cnbean.setBDown_Time(rs.getString("BDown_Time"));
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
	public BDownBean GetAllBean(int id){
		String sql="select * from BDown where BDown_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		BDownBean cnbean=new BDownBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setBDown_Id(rs.getInt("BDown_Id"));
				cnbean.setBDown_Name(rs.getString("BDown_Name"));
				cnbean.setBDown_Type(rs.getString("BDown_Type"));
				cnbean.setBDown_Unit(rs.getString("BDown_Unit"));
				cnbean.setBDown_Twarehouse(rs.getInt("BDown_Twarehouse"));
				cnbean.setBDown_Tbar(rs.getInt("BDown_Tbar"));
				cnbean.setBDown_Num(rs.getInt("BDown_Num"));
				cnbean.setBDown_Time(rs.getString("BDown_Time"));
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
	public void Add(BDownBean cnbean){
		String sql="insert into BDown (";
		sql+="BDown_Id,BDown_Name,BDown_Type,BDown_Unit,BDown_Twarehouse,BDown_Tbar,BDown_Num,BDown_Time";
		sql+=") values(";
		sql+="'"+cnbean.getBDown_Id()+"','"+cnbean.getBDown_Name()+"','"+cnbean.getBDown_Type()+"','"+cnbean.getBDown_Unit()+
				"','"+cnbean.getBDown_Twarehouse()+"','"+cnbean.getBDown_Tbar()+"','"+cnbean.getBDown_Num()+
				"','"+cnbean.getBDown_Time()+"'";
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
	public void Update(BDownBean cnbean){
		String sql="update BDown set ";
		sql+="BDown_Id='"+cnbean.getBDown_Id()+"',";
		sql+="BDown_Name='"+cnbean.getBDown_Name()+"',";
		sql+="BDown_Type='"+cnbean.getBDown_Type()+"',";
		sql+="BDown_Unit='"+cnbean.getBDown_Unit()+"',";
		sql+="BDown_Twarehouse='"+cnbean.getBDown_Twarehouse()+"',";
		sql+="BDown_Tbar='"+cnbean.getBDown_Tbar()+"',";
		sql+="BDown_Num='"+cnbean.getBDown_Num()+"',";
		sql+="BDown_Time='"+cnbean.getBDown_Time()+"'";
		
		sql+=" where BDown_Id='"+cnbean.getBDown_Id()+"'";
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
		String sql="delete from BDown where ";
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

package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class AdrecordDao {
	//获取所有列表
	public List<AdrecordBean> GetAllList(String strwhere,String strorder){
		String sql="select * from Adrecord";
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
		List<AdrecordBean> list=new ArrayList<AdrecordBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				AdrecordBean cnbean=new AdrecordBean();
				cnbean.setAdrecord_Id(rs.getInt("Adrecord_Id"));
				cnbean.setAdrecord_Username(rs.getString("Adrecord_Username"));
				cnbean.setAdrecord_Time(rs.getString("Adrecord_Time"));
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
	public AdrecordBean GetAllBean(int id){
		String sql="select * from Adrecord where Adrecord_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		AdrecordBean cnbean=new AdrecordBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setAdrecord_Id(rs.getInt("Adrecord_Id"));
				cnbean.setAdrecord_Username(rs.getString("Adrecord_Username"));
				cnbean.setAdrecord_Time(rs.getString("Adrecord_Time"));
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
	public void Add(AdrecordBean cnbean){
		String sql="insert into Adrecord (";
		sql+="Adrecord_Id,Adrecord_Username,Adrecord_Time";
		sql+=") values(";
		sql+= "null"+",'"+cnbean.getAdrecord_Username()+
				"','"+cnbean.getAdrecord_Time()+"'";
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
	public void Update(AdrecordBean cnbean){
		String sql="update Adrecord set ";
		sql+="Adrecord_Id='"+cnbean.getAdrecord_Id()+"',";
		sql+="Adrecord_Username='"+cnbean.getAdrecord_Username()+"',";
		sql+="Adrecord_Time='"+cnbean.getAdrecord_Time()+"'";
		
		sql+=" where Adrecord_Id='"+cnbean.getAdrecord_Id()+"'";
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
		String sql="delete from Adrecord where ";
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

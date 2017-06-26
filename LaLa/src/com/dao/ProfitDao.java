package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class ProfitDao {
	//获取所有列表
	public List<ProfitBean> GetAllList(String strwhere,String strorder){
		String sql="select * from Profit";
		if(!(isInvalid(strwhere)))
		{
			sql+=" where "+strwhere;
		}
		if(!(isInvalid(strorder)))
		{
			sql+=" order by "+strorder;
		}
		
		System.out.println("before the search");
		System.out.println(sql);
		
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<ProfitBean> list=new ArrayList<ProfitBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				ProfitBean cnbean=new ProfitBean();
				cnbean.setProfit_Id(rs.getInt("Profit_Id"));
				cnbean.setProfit_Username(rs.getString("Profit_Username"));
				cnbean.setProfit_Gain(rs.getInt("Profit_Gain"));
				cnbean.setProfit_Time(rs.getString("Profit_Time"));
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
	
	public ProfitBean GetAllFirstBean(String strwhere){
		String sql="select * from Profit where "+strwhere;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		ProfitBean cnbean=new ProfitBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()){
				cnbean.setProfit_Id(rs.getInt("Profit_Id"));
				cnbean.setProfit_Username(rs.getString("Profit_Username"));
				cnbean.setProfit_Gain(rs.getInt("Profit_Gain"));
				cnbean.setProfit_Time(rs.getString("Profit_Time"));
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
	public ProfitBean GetAllBean(int id){
		String sql="select * from Profit where Profit_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		ProfitBean cnbean=new ProfitBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setProfit_Id(rs.getInt("Profit_Id"));
				cnbean.setProfit_Username(rs.getString("Profit_Username"));
				cnbean.setProfit_Gain(rs.getInt("Profit_Gain"));
				cnbean.setProfit_Time(rs.getString("Profit_Time"));
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
	public void Add(ProfitBean cnbean){
		String sql="insert into Profit (";
		sql+="Profit_Id,Profit_Username,Profit_Gain,Profit_Time";
		sql+=") values(";
		sql+="null" +",'"+cnbean.getProfit_Username()+"','"+cnbean.getProfit_Gain()+"','"
				+cnbean.getProfit_Time()+"'";
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
	public void Update(ProfitBean cnbean){
		String sql="update Profit set ";
		sql+="Profit_Id='"+cnbean.getProfit_Id()+"',";
		sql+="Profit_Name='"+cnbean.getProfit_Username()+"',";
		sql+="Profit_Type='"+cnbean.getProfit_Gain()+"',";
		sql+="Profit_Time='"+cnbean.getProfit_Time()+"'";
		
		sql+=" where Profit_Id='"+cnbean.getProfit_Id()+"'";
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
		String sql="delete from Profit where ";
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

package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class GoodsDao {
	//获取所有列表
	public List<GoodsBean> GetAllList(String strwhere,String strorder){
		String sql="select * from Goods";
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
		List<GoodsBean> list=new ArrayList<GoodsBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				GoodsBean cnbean=new GoodsBean();
				cnbean.setGoods_Id(rs.getInt("Goods_Id"));
				cnbean.setGoods_Name(rs.getString("Goods_Name"));
				cnbean.setGoods_Type(rs.getString("Goods_Type"));
				cnbean.setGoods_Unit(rs.getString("Goods_Unit"));
				cnbean.setGoods_Price(rs.getInt("Goods_Price"));
				cnbean.setGoods_Warehouse(rs.getInt("Goods_Warehouse"));
				cnbean.setGoods_Bar(rs.getInt("Goods_Bar"));
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
	
	public GoodsBean GetAllFirstBean(String strwhere){
		String sql="select * from Goods where "+strwhere;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		GoodsBean cnbean=new GoodsBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			if(rs.next()){
				cnbean.setGoods_Id(rs.getInt("Goods_Id"));
				cnbean.setGoods_Name(rs.getString("Goods_Name"));
				cnbean.setGoods_Type(rs.getString("Goods_Type"));
				cnbean.setGoods_Unit(rs.getString("Goods_Unit"));
				cnbean.setGoods_Price(rs.getInt("Goods_Price"));
				cnbean.setGoods_Warehouse(rs.getInt("Goods_Warehouse"));
				cnbean.setGoods_Bar(rs.getInt("Goods_Bar"));
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
	public GoodsBean GetAllBean(int id){
		String sql="select * from Goods where Goods_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		GoodsBean cnbean=new GoodsBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setGoods_Id(rs.getInt("Goods_Id"));
				cnbean.setGoods_Name(rs.getString("Goods_Name"));
				cnbean.setGoods_Type(rs.getString("Goods_Type"));
				cnbean.setGoods_Unit(rs.getString("Goods_Unit"));
				cnbean.setGoods_Price(rs.getInt("Goods_Price"));
				cnbean.setGoods_Warehouse(rs.getInt("Goods_Warehouse"));
				cnbean.setGoods_Bar(rs.getInt("Goods_Bar"));
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
	public void Add(GoodsBean cnbean){
		String sql="insert into Goods (";
		sql+="Goods_Id,Goods_Name,Goods_Type,Goods_Unit,Goods_Price,Goods_Warehouse,Goods_Bar";
		sql+=") values(";
		sql+="null" +",'"+cnbean.getGoods_Name()+"','"+cnbean.getGoods_Type()+"','"
				+cnbean.getGoods_Unit()+"','"+cnbean.getGoods_Price()+
				"','"+cnbean.getGoods_Warehouse()+"','"+cnbean.getGoods_Bar()+"'";
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
	public void Update(GoodsBean cnbean){
		String sql="update Goods set ";
		sql+="Goods_Id='"+cnbean.getGoods_Id()+"',";
		sql+="Goods_Name='"+cnbean.getGoods_Name()+"',";
		sql+="Goods_Type='"+cnbean.getGoods_Type()+"',";
		sql+="Goods_Unit='"+cnbean.getGoods_Unit()+"',";
		sql+="Goods_Price='"+cnbean.getGoods_Price()+"',";
		sql+="Goods_Warehouse='"+cnbean.getGoods_Warehouse()+"',";
		sql+="Goods_Bar='"+cnbean.getGoods_Bar()+"'";
		
		sql+=" where Goods_Id='"+cnbean.getGoods_Id()+"'";
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
		String sql="delete from Goods where ";
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

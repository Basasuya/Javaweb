package com.dao;

import com.db.DBHelper;
import com.action.*;
import com.bean.*;

import java.util.*;

import java.sql.*;

public class GReturnDao {
	//��ȡ�����б�
	public List<GReturnBean> GetAllList(String strwhere,String strorder){
		String sql="select * from GReturn";
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
		List<GReturnBean> list=new ArrayList<GReturnBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				GReturnBean cnbean=new GReturnBean();
				cnbean.setGReturn_Id(rs.getInt("GReturn_Id"));
				cnbean.setGReturn_Name(rs.getString("GReturn_Name"));
				cnbean.setGReturn_Type(rs.getString("GReturn_Type"));
				cnbean.setGReturn_Unit(rs.getString("GReturn_Unit"));
				cnbean.setGReturn_Cost(rs.getInt("GReturn_Cost"));
				cnbean.setGReturn_Pro(rs.getInt("GReturn_Pro"));
				cnbean.setGReturn_Num(rs.getInt("GReturn_Num"));
				cnbean.setGReturn_Time(rs.getString("GReturn_Time"));
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
	

	//��ȡָ��ID��ʵ��Bean
	public GReturnBean GetAllBean(int id){
		String sql="select * from GReturn where GReturn_Id="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		GReturnBean cnbean=new GReturnBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				cnbean.setGReturn_Id(rs.getInt("GReturn_Id"));
				cnbean.setGReturn_Name(rs.getString("GReturn_Name"));
				cnbean.setGReturn_Type(rs.getString("GReturn_Type"));
				cnbean.setGReturn_Unit(rs.getString("GReturn_Unit"));
				cnbean.setGReturn_Cost(rs.getInt("GReturn_Cost"));
				cnbean.setGReturn_Pro(rs.getInt("GReturn_Pro"));
				cnbean.setGReturn_Num(rs.getInt("GReturn_Num"));
				cnbean.setGReturn_Time(rs.getString("GReturn_Time"));
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
	
	//���
	public void Add(GReturnBean cnbean){
		String sql="insert into GReturn (";
		sql+="GReturn_Id,GReturn_Name,GReturn_Type,GReturn_Unit,GReturn_Cost,GReturn_Pro,GReturn_Num,GReturn_Time";
		sql+=") values(";
		sql+="null"+",'"+cnbean.getGReturn_Name()+"','"+cnbean.getGReturn_Type()+
				"','"+cnbean.getGReturn_Unit()+"','"+cnbean.getGReturn_Cost()+"','"+cnbean.getGReturn_Pro()+"','"+
				cnbean.getGReturn_Num()+
				"','"+cnbean.getGReturn_Time()+"'";
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
	//�޸�
	public void Update(GReturnBean cnbean){
		String sql="update GReturn set ";
		sql+="GReturn_Id='"+cnbean.getGReturn_Id()+"',";
		sql+="GReturn_Name='"+cnbean.getGReturn_Name()+"',";
		sql+="GReturn_Type='"+cnbean.getGReturn_Type()+"',";
		sql+="GReturn_Unit='"+cnbean.getGReturn_Unit()+"',";
		sql+="GReturn_Cost='"+cnbean.getGReturn_Cost()+"',";
		sql+="GReturn_Pro='"+cnbean.getGReturn_Pro()+"',";
		sql+="GReturn_Num='"+cnbean.getGReturn_Num()+"',";
		sql+="GReturn_Time='"+cnbean.getGReturn_Time()+"'";
		
		sql+=" where GReturn_Id='"+cnbean.getGReturn_Id()+"'";
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
	//ɾ��
	public void Delete(String strwhere){
		String sql="delete from GReturn where ";
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

	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
	public static void main(String[] args) {
		System.out.println("");
	}
	
}

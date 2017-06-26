package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class BoodsManager extends ActionSupport {
	private List<BoodsBean> list;
	
	public List<BoodsBean> getList() {
		return list;
	}
	public void setList(List<BoodsBean> list) {
		this.list = list;
	}
	

	private String SearchRow;
	private String SearchKey;


	public String getSearchRow() {
		return SearchRow;
	}
	public void setSearchRow(String searchRow) {
		SearchRow = searchRow;
	}
	public String getSearchKey() {
		return SearchKey;
	}
	public void setSearchKey(String searchKey) {
		SearchKey = searchKey;
	}
	public String execute() throws Exception {
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('ÇëÖØÐÂµÇÂ½');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		String strWhere="1=1";
		if(!(isInvalid(SearchKey)))
		{
			strWhere+=" and "+SearchRow+"='"+SearchKey+"'";
		}
	//	System.out.println("hhhh");
	//	System.out.println(strWhere);
		list=new BoodsDao().GetAllList(strWhere,"Boods_Id");
		
		return SUCCESS;
	}
	
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
	
}

package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class GDownUpdateSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private int GDown_Id ;
    private String GDown_Name ;
    private String GDown_Type ;
    private String GDown_Unit ;
    private int GDown_Twarehouse ;
    private int GDown_Tbar;
    private int GDown_Num;
    private String GDown_Time;

	

	public int getGDown_Id() {
		return GDown_Id;
	}

	public void setGDown_Id(int gDown_Id) {
		GDown_Id = gDown_Id;
	}

	public String getGDown_Name() {
		return GDown_Name;
	}

	public void setGDown_Name(String gDown_Name) {
		GDown_Name = gDown_Name;
	}

	public String getGDown_Type() {
		return GDown_Type;
	}

	public void setGDown_Type(String gDown_Type) {
		GDown_Type = gDown_Type;
	}

	public String getGDown_Unit() {
		return GDown_Unit;
	}

	public void setGDown_Unit(String gDown_Unit) {
		GDown_Unit = gDown_Unit;
	}

	public int getGDown_Twarehouse() {
		return GDown_Twarehouse;
	}

	public void setGDown_Twarehouse(int gDown_Twarehouse) {
		GDown_Twarehouse = gDown_Twarehouse;
	}

	public int getGDown_Tbar() {
		return GDown_Tbar;
	}

	public void setGDown_Tbar(int gDown_Tbar) {
		GDown_Tbar = gDown_Tbar;
	}

	public int getGDown_Num() {
		return GDown_Num;
	}

	public void setGDown_Num(int gDown_Num) {
		GDown_Num = gDown_Num;
	}

	public String getGDown_Time() {
		return GDown_Time;
	}

	public void setGDown_Time(String gDown_Time) {
		GDown_Time = gDown_Time;
	}

	//处理用户请求的execute方法
	public String execute() throws Exception {
		
		//解决乱码，用于页面输出
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		//验证是否正常登录
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		
		
		GDownBean cnbean=new GDownBean();
		cnbean=new GDownDao().GetAllBean(GDown_Id);
		
		cnbean.setGDown_Name(GDown_Name);
		cnbean.setGDown_Type(GDown_Type);
		cnbean.setGDown_Unit(GDown_Unit);
		cnbean.setGDown_Twarehouse(GDown_Twarehouse);
		cnbean.setGDown_Tbar(GDown_Tbar);
		cnbean.setGDown_Num(GDown_Num);
		cnbean.setGDown_Time(GDown_Time);
		
		new GDownDao().Update(cnbean);
		
		    
		//跳转
		out.print("<script language='javascript'>alert('修改成功！');window.location='GDownManager.action';</script>");
		out.flush();out.close();return null;
		
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}
	
}

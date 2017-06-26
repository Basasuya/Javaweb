package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class GReturnUpdateSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private int GReturn_Id ;
    private String GReturn_Name ;
    private String GReturn_Type ;
    private String GReturn_Unit;
    private int GReturn_Cost;
    private int GReturn_Pro;
    private int GReturn_Num;
    private String GReturn_Time;

	public int getGReturn_Id() {
		return GReturn_Id;
	}

	public void setGReturn_Id(int gStock_Id) {
		GReturn_Id = gStock_Id;
	}

	public String getGReturn_Name() {
		return GReturn_Name;
	}
	
	public String getGReturn_Unit() {
		return GReturn_Unit;
	}

	public void setGReturn_Unit(String gStock_Unit) {
		GReturn_Unit = gStock_Unit;
	}

	public int getGReturn_Cost() {
		return GReturn_Cost;
	}

	public void setGReturn_Cost(int gStock_Cost) {
		GReturn_Cost = gStock_Cost;
	}

	public int getGReturn_Pro() {
		return GReturn_Pro;
	}

	public void setGReturn_Pro(int gStock_Pro) {
		GReturn_Pro = gStock_Pro;
	}

	public void setGReturn_Name(String gStock_Name) {
		GReturn_Name = gStock_Name;
	}

	public String getGReturn_Type() {
		return GReturn_Type;
	}

	public void setGReturn_Type(String gStock_Type) {
		GReturn_Type = gStock_Type;
	}

	public int getGReturn_Num() {
		return GReturn_Num;
	}

	public void setGReturn_Num(int gStock_Num) {
		GReturn_Num = gStock_Num;
	}

	public String getGReturn_Time() {
		return GReturn_Time;
	}

	public void setGReturn_Time(String gStock_Time) {
		GReturn_Time = gStock_Time;
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
		
		
		GReturnBean cnbean=new GReturnBean();
		cnbean=new GReturnDao().GetAllBean(GReturn_Id);
		
		cnbean.setGReturn_Name(GReturn_Name);
		cnbean.setGReturn_Type(GReturn_Type);
		cnbean.setGReturn_Unit(GReturn_Unit);
		cnbean.setGReturn_Cost(GReturn_Cost);
		cnbean.setGReturn_Pro(GReturn_Pro);
		cnbean.setGReturn_Num(GReturn_Num);
		cnbean.setGReturn_Time(GReturn_Time);
		
		System.out.println(GReturn_Time+" "+GReturn_Num);
		
		new GReturnDao().Update(cnbean);
		    
		//跳转
		out.print("<script language='javascript'>alert('修改成功！');window.location='GReturnManager.action';</script>");
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

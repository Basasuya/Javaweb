package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class BUpUpdateSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private int BUp_Id ;
    private String BUp_Name ;
    private String BUp_Type ;
    private String BUp_Unit ;
    private int BUp_Twarehouse ;
    private int BUp_Tbar;
    private int BUp_Num;
    private String BUp_Time;

	public int getBUp_Id() {
		return BUp_Id;
	}

	public void setBUp_Id(int gUp_Id) {
		BUp_Id = gUp_Id;
	}

	public String getBUp_Name() {
		return BUp_Name;
	}

	public void setBUp_Name(String gUp_Name) {
		BUp_Name = gUp_Name;
	}

	public String getBUp_Type() {
		return BUp_Type;
	}

	public void setBUp_Type(String gUp_Type) {
		BUp_Type = gUp_Type;
	}

	public String getBUp_Unit() {
		return BUp_Unit;
	}

	public void setBUp_Unit(String gUp_Unit) {
		BUp_Unit = gUp_Unit;
	}

	public int getBUp_Twarehouse() {
		return BUp_Twarehouse;
	}

	public void setBUp_Twarehouse(int gUp_Twarehouse) {
		BUp_Twarehouse = gUp_Twarehouse;
	}

	public int getBUp_Tbar() {
		return BUp_Tbar;
	}

	public void setBUp_Tbar(int gUp_Tbar) {
		BUp_Tbar = gUp_Tbar;
	}

	public int getBUp_Num() {
		return BUp_Num;
	}

	public void setBUp_Num(int gUp_Num) {
		BUp_Num = gUp_Num;
	}

	public String getBUp_Time() {
		return BUp_Time;
	}

	public void setBUp_Time(String gUp_Time) {
		BUp_Time = gUp_Time;
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
		
		
		BUpBean cnbean=new BUpBean();
		cnbean=new BUpDao().GetAllBean(BUp_Id);
		
		cnbean.setBUp_Name(BUp_Name);
		cnbean.setBUp_Type(BUp_Type);
		cnbean.setBUp_Unit(BUp_Unit);
		cnbean.setBUp_Twarehouse(BUp_Twarehouse);
		cnbean.setBUp_Tbar(BUp_Tbar);
		cnbean.setBUp_Num(BUp_Num);
		cnbean.setBUp_Time(BUp_Time);
		
		System.out.println(BUp_Time+" "+BUp_Num);
		
		new BUpDao().Update(cnbean);
		    
		//跳转
		out.print("<script language='javascript'>alert('修改成功！');window.location='BUpManager.action';</script>");
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

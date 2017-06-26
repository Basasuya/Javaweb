package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class BDownUpdateSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private int BDown_Id ;
    private String BDown_Name ;
    private String BDown_Type ;
    private String BDown_Unit ;
    private int BDown_Twarehouse ;
    private int BDown_Tbar;
    private int BDown_Num;
    private String BDown_Time;

	

	public int getBDown_Id() {
		return BDown_Id;
	}

	public void setBDown_Id(int gDown_Id) {
		BDown_Id = gDown_Id;
	}

	public String getBDown_Name() {
		return BDown_Name;
	}

	public void setBDown_Name(String gDown_Name) {
		BDown_Name = gDown_Name;
	}

	public String getBDown_Type() {
		return BDown_Type;
	}

	public void setBDown_Type(String gDown_Type) {
		BDown_Type = gDown_Type;
	}

	public String getBDown_Unit() {
		return BDown_Unit;
	}

	public void setBDown_Unit(String gDown_Unit) {
		BDown_Unit = gDown_Unit;
	}

	public int getBDown_Twarehouse() {
		return BDown_Twarehouse;
	}

	public void setBDown_Twarehouse(int gDown_Twarehouse) {
		BDown_Twarehouse = gDown_Twarehouse;
	}

	public int getBDown_Tbar() {
		return BDown_Tbar;
	}

	public void setBDown_Tbar(int gDown_Tbar) {
		BDown_Tbar = gDown_Tbar;
	}

	public int getBDown_Num() {
		return BDown_Num;
	}

	public void setBDown_Num(int gDown_Num) {
		BDown_Num = gDown_Num;
	}

	public String getBDown_Time() {
		return BDown_Time;
	}

	public void setBDown_Time(String gDown_Time) {
		BDown_Time = gDown_Time;
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
		
		
		BDownBean cnbean=new BDownBean();
		cnbean=new BDownDao().GetAllBean(BDown_Id);
		
		cnbean.setBDown_Name(BDown_Name);
		cnbean.setBDown_Type(BDown_Type);
		cnbean.setBDown_Unit(BDown_Unit);
		cnbean.setBDown_Twarehouse(BDown_Twarehouse);
		cnbean.setBDown_Tbar(BDown_Tbar);
		cnbean.setBDown_Num(BDown_Num);
		cnbean.setBDown_Time(BDown_Time);
		
		System.out.println(BDown_Time+" "+BDown_Num);
		
		new BDownDao().Update(cnbean);
		    
		//跳转
		out.print("<script language='javascript'>alert('修改成功！');window.location='BDownManager.action';</script>");
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

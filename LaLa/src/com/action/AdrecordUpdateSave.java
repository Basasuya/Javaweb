package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class AdrecordUpdateSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private int Adrecord_Id ;
    private String Adrecord_Username ;
    private String Adrecord_Time;


	public int getAdrecord_Id() {
		return Adrecord_Id;
	}

	public void setAdrecord_Id(int adrecord_Id) {
		Adrecord_Id = adrecord_Id;
	}

	public String getAdrecord_Username() {
		return Adrecord_Username;
	}

	public void setAdrecord_Username(String adrecord_Username) {
		Adrecord_Username = adrecord_Username;
	}

	public String getAdrecord_Time() {
		return Adrecord_Time;
	}

	public void setAdrecord_Time(String adrecord_Time) {
		Adrecord_Time = adrecord_Time;
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
		
		
		AdrecordBean cnbean=new AdrecordBean();
		cnbean=new AdrecordDao().GetAllBean(Adrecord_Id);
		
		cnbean.setAdrecord_Username(Adrecord_Username);
		cnbean.setAdrecord_Time(Adrecord_Time);
		
	//	System.out.println(Adrecord_Time+" "+Adrecord_Num);
		
		new AdrecordDao().Update(cnbean);
		    
		//跳转
		out.print("<script language='javascript'>alert('修改成功！');window.location='AdrecordManager.action';</script>");
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

package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class BStockUpdateSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private int BStock_Id ;
    private String BStock_Name ;
    private String BStock_Type ;
    private String BStock_Unit;
    private int BStock_Cost;
    private int BStock_Pro;
    private int BStock_Num;
    private String BStock_Time;

	public int getBStock_Id() {
		return BStock_Id;
	}

	public void setBStock_Id(int gStock_Id) {
		BStock_Id = gStock_Id;
	}

	public String getBStock_Name() {
		return BStock_Name;
	}
	
	public String getBStock_Unit() {
		return BStock_Unit;
	}

	public void setBStock_Unit(String gStock_Unit) {
		BStock_Unit = gStock_Unit;
	}

	public int getBStock_Cost() {
		return BStock_Cost;
	}

	public void setBStock_Cost(int gStock_Cost) {
		BStock_Cost = gStock_Cost;
	}

	public int getBStock_Pro() {
		return BStock_Pro;
	}

	public void setBStock_Pro(int gStock_Pro) {
		BStock_Pro = gStock_Pro;
	}

	public void setBStock_Name(String gStock_Name) {
		BStock_Name = gStock_Name;
	}

	public String getBStock_Type() {
		return BStock_Type;
	}

	public void setBStock_Type(String gStock_Type) {
		BStock_Type = gStock_Type;
	}

	public int getBStock_Num() {
		return BStock_Num;
	}

	public void setBStock_Num(int gStock_Num) {
		BStock_Num = gStock_Num;
	}

	public String getBStock_Time() {
		return BStock_Time;
	}

	public void setBStock_Time(String gStock_Time) {
		BStock_Time = gStock_Time;
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
		
		
		BStockBean cnbean=new BStockBean();
		cnbean=new BStockDao().GetAllBean(BStock_Id);
		
		cnbean.setBStock_Name(BStock_Name);
		cnbean.setBStock_Type(BStock_Type);
		cnbean.setBStock_Unit(BStock_Unit);
		cnbean.setBStock_Cost(BStock_Cost);
		cnbean.setBStock_Pro(BStock_Pro);
		cnbean.setBStock_Num(BStock_Num);
		cnbean.setBStock_Time(BStock_Time);
		
		System.out.println(BStock_Time+" "+BStock_Num);
		
		new BStockDao().Update(cnbean);
		    
		//跳转
		out.print("<script language='javascript'>alert('修改成功！');window.location='BStockManager.action';</script>");
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

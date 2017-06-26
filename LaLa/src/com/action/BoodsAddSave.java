package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class BoodsAddSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private int Boods_Id ;
    private String Boods_Name ;
    private String Boods_Type ;
    private String Boods_Unit;
    private int Boods_Price; 
    private int Boods_Warehouse;
    private int Boods_Bar;

	

	public int getBoods_Id() {
		return Boods_Id;
	}

	public void setBoods_Id(int goods_Id) {
		Boods_Id = goods_Id;
	}

	public String getBoods_Name() {
		return Boods_Name;
	}

	public void setBoods_Name(String goods_Name) {
		Boods_Name = goods_Name;
	}

	public String getBoods_Type() {
		return Boods_Type;
	}

	public void setBoods_Type(String goods_Type) {
		Boods_Type = goods_Type;
	}

	public int getBoods_Price() {
		return Boods_Price;
	}

	public void setBoods_Price(int goods_Price) {
		Boods_Price = goods_Price;
	}
	
	public String getBoods_Unit() {
		return Boods_Unit;
	}

	public void setBoods_Unit(String goods_Unit) {
		Boods_Unit = goods_Unit;
	}


	public int getBoods_Warehouse() {
		return Boods_Warehouse;
	}

	public void setBoods_Warehouse(int goods_Warehouse) {
		Boods_Warehouse = goods_Warehouse;
	}

	public int getBoods_Bar() {
		return Boods_Bar;
	}

	public void setBoods_Bar(int goods_Bar) {
		Boods_Bar = goods_Bar;
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
		
		//查询用户名是否存在
		List<BoodsBean> list=new BoodsDao().GetAllList("Boods_Name='"+Boods_Name+"'", "");
		if(list.size()>0)
		{
			out.print("<script language='javascript'>alert('用户名已经存在！');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		
		
		BoodsBean cnbean=new BoodsBean();
		cnbean.setBoods_Name(Boods_Name);
		cnbean.setBoods_Type(Boods_Type);
		cnbean.setBoods_Price(Boods_Price);
		cnbean.setBoods_Unit(Boods_Unit);
		cnbean.setBoods_Warehouse(Boods_Warehouse);
		cnbean.setBoods_Bar(Boods_Bar);
		
		new BoodsDao().Add(cnbean);
		
		//跳转
		out.print("<script language='javascript'>alert('添加成功！');window.location='BoodsManager.action';</script>");
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

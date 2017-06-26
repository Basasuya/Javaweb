package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class GoodsAddSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private int Goods_Id ;
    private String Goods_Name ;
    private String Goods_Type ;
    private String Goods_Unit;
    private int Goods_Price; 
    private int Goods_Warehouse;
    private int Goods_Bar;

	

	public int getGoods_Id() {
		return Goods_Id;
	}

	public void setGoods_Id(int goods_Id) {
		Goods_Id = goods_Id;
	}

	public String getGoods_Name() {
		return Goods_Name;
	}

	public void setGoods_Name(String goods_Name) {
		Goods_Name = goods_Name;
	}

	public String getGoods_Type() {
		return Goods_Type;
	}

	public void setGoods_Type(String goods_Type) {
		Goods_Type = goods_Type;
	}

	public int getGoods_Price() {
		return Goods_Price;
	}

	public void setGoods_Price(int goods_Price) {
		Goods_Price = goods_Price;
	}
	
	public String getGoods_Unit() {
		return Goods_Unit;
	}

	public void setGoods_Unit(String goods_Unit) {
		Goods_Unit = goods_Unit;
	}


	public int getGoods_Warehouse() {
		return Goods_Warehouse;
	}

	public void setGoods_Warehouse(int goods_Warehouse) {
		Goods_Warehouse = goods_Warehouse;
	}

	public int getGoods_Bar() {
		return Goods_Bar;
	}

	public void setGoods_Bar(int goods_Bar) {
		Goods_Bar = goods_Bar;
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
		List<GoodsBean> list=new GoodsDao().GetAllList("Goods_Name='"+Goods_Name+"'", "");
		if(list.size()>0)
		{
			out.print("<script language='javascript'>alert('用户名已经存在！');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		
		
		GoodsBean cnbean=new GoodsBean();
		cnbean.setGoods_Name(Goods_Name);
		cnbean.setGoods_Type(Goods_Type);
		cnbean.setGoods_Price(Goods_Price);
		cnbean.setGoods_Unit(Goods_Unit);
		cnbean.setGoods_Warehouse(Goods_Warehouse);
		cnbean.setGoods_Bar(Goods_Bar);
		
		new GoodsDao().Add(cnbean);
		
		//跳转
		out.print("<script language='javascript'>alert('添加成功！');window.location='GoodsManager.action';</script>");
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

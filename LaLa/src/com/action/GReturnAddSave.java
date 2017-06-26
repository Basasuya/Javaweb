package com.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class GReturnAddSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
    private String GReturn_Name ;
    private int GReturn_Cost;
    private int GReturn_Num ;

	public String getGReturn_Name() {
		return GReturn_Name;
	}

	public void setGReturn_Name(String gUp_Name) {
		GReturn_Name = gUp_Name;
	}

	public int getGReturn_Cost() {
		return GReturn_Cost;
	}

	public void setGReturn_Cost(int gStock_Cost) {
		GReturn_Cost = gStock_Cost;
	}

	public int getGReturn_Num() {
		return GReturn_Num;
	}

	public void setGReturn_Num(int gUp_Num) {
		GReturn_Num = gUp_Num;
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

		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		
		//查询用户名是否存在

		GoodsBean tmpbean = new GoodsDao(). GetAllFirstBean("Goods_Name='"+GReturn_Name+"'");
		if(tmpbean.getGoods_Id() == 0) {
			out.print("<script language='javascript'>alert('不存在该商品');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		if(tmpbean.getGoods_Warehouse() < GReturn_Num) {
			out.print("<script language='javascript'>alert('商品数量不够');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		//添加
		
		   
		tmpbean.setGoods_Warehouse(tmpbean.getGoods_Warehouse() - GReturn_Num);
		new GoodsDao().Update(tmpbean);
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time = dateFormat.format( now ); 
		System.out.println("GReturn_Cost = "+GReturn_Cost);
		int tmp = GReturn_Cost* GReturn_Num;
		GReturnBean cnbean=new GReturnBean();
		cnbean.setGReturn_Name(GReturn_Name);
		cnbean.setGReturn_Type(tmpbean.getGoods_Type());
		cnbean.setGReturn_Unit(tmpbean.getGoods_Unit());
		cnbean.setGReturn_Cost(GReturn_Cost);
		cnbean.setGReturn_Pro(tmp);
		cnbean.setGReturn_Num(GReturn_Num);
		cnbean.setGReturn_Time(Time);
		
		new GReturnDao().Add(cnbean);
		
		int fl = Integer.parseInt(session.getAttribute("id").toString());
		AdminBean t2 = new AdminDao().GetAllBean(fl);
		ProfitBean t3 = new ProfitBean();
		t3.setProfit_Username(t2.getAdmin_Username());
		t3.setProfit_Time(Time);
		t3.setProfit_Gain(tmp);
		new ProfitDao().Add(t3);
		
		//跳转
		out.print("<script language='javascript'>alert('添加成功！');window.location='GReturnManager.action';</script>");
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

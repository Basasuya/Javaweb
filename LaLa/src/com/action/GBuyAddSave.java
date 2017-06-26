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


public class GBuyAddSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
    private String GBuy_Name ;
    private int GBuy_Num ;

	public String getGBuy_Name() {
		return GBuy_Name;
	}

	public void setGBuy_Name(String gUp_Name) {
		GBuy_Name = gUp_Name;
	}

	public int getGBuy_Num() {
		return GBuy_Num;
	}

	public void setGBuy_Num(int gUp_Num) {
		GBuy_Num = gUp_Num;
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

		GoodsBean tmpbean = new GoodsDao(). GetAllFirstBean("Goods_Name='"+GBuy_Name+"'");
		if(tmpbean.getGoods_Id() == 0) {
			out.print("<script language='javascript'>alert('不存在该商品');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		if(tmpbean.getGoods_Bar() - GBuy_Num < 0) {
			out.print("<script language='javascript'>alert('商品数量不够');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		   
		tmpbean.setGoods_Bar(tmpbean.getGoods_Bar() - GBuy_Num);
		new GoodsDao().Update(tmpbean);
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time = dateFormat.format( now ); 
		
		int tmp = tmpbean.getGoods_Price()* GBuy_Num;
		
		GBuyBean cnbean=new GBuyBean();
		cnbean.setGBuy_Name(GBuy_Name);
		cnbean.setGBuy_Type(tmpbean.getGoods_Type());
		cnbean.setGBuy_Unit(tmpbean.getGoods_Unit());
		cnbean.setGBuy_Pro(tmp);
		cnbean.setGBuy_Num(GBuy_Num);
		cnbean.setGBuy_Time(Time);
		
		new GBuyDao().Add(cnbean);
		
		int fl = Integer.parseInt(session.getAttribute("id").toString());
		AdminBean t2 = new AdminDao().GetAllBean(fl);
		ProfitBean t3 = new ProfitBean();
		t3.setProfit_Username(t2.getAdmin_Username());
		t3.setProfit_Time(Time);
		t3.setProfit_Gain(tmp);
		new ProfitDao().Add(t3);
		
		
		//跳转
		out.print("<script language='javascript'>alert('添加成功！');window.location='GBuyManager.action';</script>");
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

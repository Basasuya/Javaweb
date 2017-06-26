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


public class BReturnAddSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
    private String BReturn_Name ;
    private int BReturn_Cost;
    private int BReturn_Num ;

	public String getBReturn_Name() {
		return BReturn_Name;
	}

	public void setBReturn_Name(String gUp_Name) {
		BReturn_Name = gUp_Name;
	}

	public int getBReturn_Cost() {
		return BReturn_Cost;
	}

	public void setBReturn_Cost(int gReturn_Cost) {
		BReturn_Cost = gReturn_Cost;
	}

	public int getBReturn_Num() {
		return BReturn_Num;
	}

	public void setBReturn_Num(int gUp_Num) {
		BReturn_Num = gUp_Num;
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

		BoodsBean tmpbean = new BoodsDao(). GetAllFirstBean("Boods_Name='"+BReturn_Name+"'");
		if(tmpbean.getBoods_Id() == 0) {
			out.print("<script language='javascript'>alert('不存在该物料');history.back(-1);</script>");
			out.flush();out.close();return null;
		}

		//添加
		
		   
		tmpbean.setBoods_Warehouse(tmpbean.getBoods_Warehouse() + BReturn_Num);
		new BoodsDao().Update(tmpbean);
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time = dateFormat.format( now ); 
		System.out.println("BReturn_Cost = "+BReturn_Cost);
		int tmp = BReturn_Cost* BReturn_Num;
		BReturnBean cnbean=new BReturnBean();
		cnbean.setBReturn_Name(BReturn_Name);
		cnbean.setBReturn_Type(tmpbean.getBoods_Type());
		cnbean.setBReturn_Unit(tmpbean.getBoods_Unit());
		cnbean.setBReturn_Cost(BReturn_Cost);
		cnbean.setBReturn_Pro(tmp);
		cnbean.setBReturn_Num(BReturn_Num);
		cnbean.setBReturn_Time(Time);
		
		new BReturnDao().Add(cnbean);
		
		int fl = Integer.parseInt(session.getAttribute("id").toString());
		AdminBean t2 = new AdminDao().GetAllBean(fl);
		ProfitBean t3 = new ProfitBean();
		t3.setProfit_Username(t2.getAdmin_Username());
		t3.setProfit_Time(Time);
		t3.setProfit_Gain(tmp);
		new ProfitDao().Add(t3);
		
		//跳转
		out.print("<script language='javascript'>alert('添加成功！');window.location='BReturnManager.action';</script>");
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

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


public class BBuyAddSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
    private String BBuy_Name ;
    private int BBuy_Num ;

	public String getBBuy_Name() {
		return BBuy_Name;
	}

	public void setBBuy_Name(String gUp_Name) {
		BBuy_Name = gUp_Name;
	}

	public int getBBuy_Num() {
		return BBuy_Num;
	}

	public void setBBuy_Num(int gUp_Num) {
		BBuy_Num = gUp_Num;
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

		BoodsBean tmpbean = new BoodsDao(). GetAllFirstBean("Boods_Name='"+BBuy_Name+"'");
		if(tmpbean.getBoods_Id() == 0) {
			out.print("<script language='javascript'>alert('不存在该物料');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		if(tmpbean.getBoods_Bar() - BBuy_Num < 0) {
			out.print("<script language='javascript'>alert('物料数量不够');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		   
		tmpbean.setBoods_Bar(tmpbean.getBoods_Bar() - BBuy_Num);
		new BoodsDao().Update(tmpbean);
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time = dateFormat.format( now ); 
		
		int tmp = tmpbean.getBoods_Price()* BBuy_Num;
		
		BBuyBean cnbean=new BBuyBean();
		cnbean.setBBuy_Name(BBuy_Name);
		cnbean.setBBuy_Type(tmpbean.getBoods_Type());
		cnbean.setBBuy_Unit(tmpbean.getBoods_Unit());
		cnbean.setBBuy_Pro(tmp);
		cnbean.setBBuy_Num(BBuy_Num);
		cnbean.setBBuy_Time(Time);
		
		new BBuyDao().Add(cnbean);
		
		int fl = Integer.parseInt(session.getAttribute("id").toString());
		AdminBean t2 = new AdminDao().GetAllBean(fl);
		ProfitBean t3 = new ProfitBean();
		t3.setProfit_Username(t2.getAdmin_Username());
		t3.setProfit_Time(Time);
		t3.setProfit_Gain(tmp);
		new ProfitDao().Add(t3);
		
		
		//跳转
		out.print("<script language='javascript'>alert('添加成功！');window.location='BBuyManager.action';</script>");
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

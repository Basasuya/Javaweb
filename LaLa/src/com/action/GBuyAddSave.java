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

	//������Action�����ڷ�װ�û��������������
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



	//�����û������execute����
	public String execute() throws Exception {
		
		//������룬����ҳ�����
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//����session����
		HttpSession session = ServletActionContext.getRequest().getSession();

		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('�����µ�¼��');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		
		//��ѯ�û����Ƿ����

		GoodsBean tmpbean = new GoodsDao(). GetAllFirstBean("Goods_Name='"+GBuy_Name+"'");
		if(tmpbean.getGoods_Id() == 0) {
			out.print("<script language='javascript'>alert('�����ڸ���Ʒ');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		if(tmpbean.getGoods_Bar() - GBuy_Num < 0) {
			out.print("<script language='javascript'>alert('��Ʒ��������');history.back(-1);</script>");
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
		
		
		//��ת
		out.print("<script language='javascript'>alert('��ӳɹ���');window.location='GBuyManager.action';</script>");
		out.flush();out.close();return null;
		
	}
	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
	public static void main(String[] args) {
		System.out.println();
	}
	
}

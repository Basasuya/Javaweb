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

	//������Action�����ڷ�װ�û��������������
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

		GoodsBean tmpbean = new GoodsDao(). GetAllFirstBean("Goods_Name='"+GReturn_Name+"'");
		if(tmpbean.getGoods_Id() == 0) {
			out.print("<script language='javascript'>alert('�����ڸ���Ʒ');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		if(tmpbean.getGoods_Warehouse() < GReturn_Num) {
			out.print("<script language='javascript'>alert('��Ʒ��������');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		//���
		
		   
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
		
		//��ת
		out.print("<script language='javascript'>alert('��ӳɹ���');window.location='GReturnManager.action';</script>");
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

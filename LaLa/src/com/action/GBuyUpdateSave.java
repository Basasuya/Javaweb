package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class GBuyUpdateSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int GBuy_Id ;
    private String GBuy_Name ;
    private String GBuy_Type ;
    private String GBuy_Unit ;
    private int GBuy_Pro;
    private int GBuy_Num;
    private String GBuy_Time;

	public int getGBuy_Id() {
		return GBuy_Id;
	}

	public void setGBuy_Id(int gBuy_Id) {
		GBuy_Id = gBuy_Id;
	}

	public String getGBuy_Name() {
		return GBuy_Name;
	}

	public void setGBuy_Name(String gBuy_Name) {
		GBuy_Name = gBuy_Name;
	}

	public String getGBuy_Type() {
		return GBuy_Type;
	}

	public void setGBuy_Type(String gBuy_Type) {
		GBuy_Type = gBuy_Type;
	}

	public int getGBuy_Num() {
		return GBuy_Num;
	}
	
	public String getGBuy_Unit() {
		return GBuy_Unit;
	}

	public void setGBuy_Unit(String gBuy_Unit) {
		GBuy_Unit = gBuy_Unit;
	}

	public int getGBuy_Pro() {
		return GBuy_Pro;
	}

	public void setGBuy_Pro(int gBuy_Pro) {
		GBuy_Pro = gBuy_Pro;
	}

	public void setGBuy_Num(int gBuy_Num) {
		GBuy_Num = gBuy_Num;
	}

	public String getGBuy_Time() {
		return GBuy_Time;
	}

	public void setGBuy_Time(String gBuy_Time) {
		GBuy_Time = gBuy_Time;
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
		//��֤�Ƿ�������¼
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('�����µ�¼��');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		
		
		GBuyBean cnbean=new GBuyBean();
		cnbean=new GBuyDao().GetAllBean(GBuy_Id);
		
		cnbean.setGBuy_Name(GBuy_Name);
		cnbean.setGBuy_Type(GBuy_Type);
		cnbean.setGBuy_Unit(GBuy_Unit);
		cnbean.setGBuy_Pro(GBuy_Pro);
		cnbean.setGBuy_Num(GBuy_Num);
		cnbean.setGBuy_Time(GBuy_Time);
		
		System.out.println(GBuy_Time+" "+GBuy_Num);
		
		new GBuyDao().Update(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('�޸ĳɹ���');window.location='GBuyManager.action';</script>");
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

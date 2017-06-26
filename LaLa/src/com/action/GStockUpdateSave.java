package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class GStockUpdateSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int GStock_Id ;
    private String GStock_Name ;
    private String GStock_Type ;
    private String GStock_Unit;
    private int GStock_Cost;
    private int GStock_Pro;
    private int GStock_Num;
    private String GStock_Time;

	public int getGStock_Id() {
		return GStock_Id;
	}

	public void setGStock_Id(int gStock_Id) {
		GStock_Id = gStock_Id;
	}

	public String getGStock_Name() {
		return GStock_Name;
	}
	
	public String getGStock_Unit() {
		return GStock_Unit;
	}

	public void setGStock_Unit(String gStock_Unit) {
		GStock_Unit = gStock_Unit;
	}

	public int getGStock_Cost() {
		return GStock_Cost;
	}

	public void setGStock_Cost(int gStock_Cost) {
		GStock_Cost = gStock_Cost;
	}

	public int getGStock_Pro() {
		return GStock_Pro;
	}

	public void setGStock_Pro(int gStock_Pro) {
		GStock_Pro = gStock_Pro;
	}

	public void setGStock_Name(String gStock_Name) {
		GStock_Name = gStock_Name;
	}

	public String getGStock_Type() {
		return GStock_Type;
	}

	public void setGStock_Type(String gStock_Type) {
		GStock_Type = gStock_Type;
	}

	public int getGStock_Num() {
		return GStock_Num;
	}

	public void setGStock_Num(int gStock_Num) {
		GStock_Num = gStock_Num;
	}

	public String getGStock_Time() {
		return GStock_Time;
	}

	public void setGStock_Time(String gStock_Time) {
		GStock_Time = gStock_Time;
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
		
		
		GStockBean cnbean=new GStockBean();
		cnbean=new GStockDao().GetAllBean(GStock_Id);
		
		cnbean.setGStock_Name(GStock_Name);
		cnbean.setGStock_Type(GStock_Type);
		cnbean.setGStock_Unit(GStock_Unit);
		cnbean.setGStock_Cost(GStock_Cost);
		cnbean.setGStock_Pro(GStock_Pro);
		cnbean.setGStock_Num(GStock_Num);
		cnbean.setGStock_Time(GStock_Time);
		
		System.out.println(GStock_Time+" "+GStock_Num);
		
		new GStockDao().Update(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('�޸ĳɹ���');window.location='GStockManager.action';</script>");
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

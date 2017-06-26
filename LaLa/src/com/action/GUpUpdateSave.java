package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class GUpUpdateSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int GUp_Id ;
    private String GUp_Name ;
    private String GUp_Type ;
    private String GUp_Unit ;
    private int GUp_Twarehouse ;
    private int GUp_Tbar;
    private int GUp_Num;
    private String GUp_Time;

	public int getGUp_Id() {
		return GUp_Id;
	}

	public void setGUp_Id(int gUp_Id) {
		GUp_Id = gUp_Id;
	}

	public String getGUp_Name() {
		return GUp_Name;
	}

	public void setGUp_Name(String gUp_Name) {
		GUp_Name = gUp_Name;
	}

	public String getGUp_Type() {
		return GUp_Type;
	}

	public void setGUp_Type(String gUp_Type) {
		GUp_Type = gUp_Type;
	}

	public String getGUp_Unit() {
		return GUp_Unit;
	}

	public void setGUp_Unit(String gUp_Unit) {
		GUp_Unit = gUp_Unit;
	}

	public int getGUp_Twarehouse() {
		return GUp_Twarehouse;
	}

	public void setGUp_Twarehouse(int gUp_Twarehouse) {
		GUp_Twarehouse = gUp_Twarehouse;
	}

	public int getGUp_Tbar() {
		return GUp_Tbar;
	}

	public void setGUp_Tbar(int gUp_Tbar) {
		GUp_Tbar = gUp_Tbar;
	}

	public int getGUp_Num() {
		return GUp_Num;
	}

	public void setGUp_Num(int gUp_Num) {
		GUp_Num = gUp_Num;
	}

	public String getGUp_Time() {
		return GUp_Time;
	}

	public void setGUp_Time(String gUp_Time) {
		GUp_Time = gUp_Time;
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
		
		
		GUpBean cnbean=new GUpBean();
		cnbean=new GUpDao().GetAllBean(GUp_Id);
		
		cnbean.setGUp_Name(GUp_Name);
		cnbean.setGUp_Type(GUp_Type);
		cnbean.setGUp_Unit(GUp_Unit);
		cnbean.setGUp_Twarehouse(GUp_Twarehouse);
		cnbean.setGUp_Tbar(GUp_Tbar);
		cnbean.setGUp_Num(GUp_Num);
		cnbean.setGUp_Time(GUp_Time);
		
		System.out.println(GUp_Time+" "+GUp_Num);
		
		new GUpDao().Update(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('�޸ĳɹ���');window.location='GUpManager.action';</script>");
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

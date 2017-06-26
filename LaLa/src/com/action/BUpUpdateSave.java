package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class BUpUpdateSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int BUp_Id ;
    private String BUp_Name ;
    private String BUp_Type ;
    private String BUp_Unit ;
    private int BUp_Twarehouse ;
    private int BUp_Tbar;
    private int BUp_Num;
    private String BUp_Time;

	public int getBUp_Id() {
		return BUp_Id;
	}

	public void setBUp_Id(int gUp_Id) {
		BUp_Id = gUp_Id;
	}

	public String getBUp_Name() {
		return BUp_Name;
	}

	public void setBUp_Name(String gUp_Name) {
		BUp_Name = gUp_Name;
	}

	public String getBUp_Type() {
		return BUp_Type;
	}

	public void setBUp_Type(String gUp_Type) {
		BUp_Type = gUp_Type;
	}

	public String getBUp_Unit() {
		return BUp_Unit;
	}

	public void setBUp_Unit(String gUp_Unit) {
		BUp_Unit = gUp_Unit;
	}

	public int getBUp_Twarehouse() {
		return BUp_Twarehouse;
	}

	public void setBUp_Twarehouse(int gUp_Twarehouse) {
		BUp_Twarehouse = gUp_Twarehouse;
	}

	public int getBUp_Tbar() {
		return BUp_Tbar;
	}

	public void setBUp_Tbar(int gUp_Tbar) {
		BUp_Tbar = gUp_Tbar;
	}

	public int getBUp_Num() {
		return BUp_Num;
	}

	public void setBUp_Num(int gUp_Num) {
		BUp_Num = gUp_Num;
	}

	public String getBUp_Time() {
		return BUp_Time;
	}

	public void setBUp_Time(String gUp_Time) {
		BUp_Time = gUp_Time;
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
		
		
		BUpBean cnbean=new BUpBean();
		cnbean=new BUpDao().GetAllBean(BUp_Id);
		
		cnbean.setBUp_Name(BUp_Name);
		cnbean.setBUp_Type(BUp_Type);
		cnbean.setBUp_Unit(BUp_Unit);
		cnbean.setBUp_Twarehouse(BUp_Twarehouse);
		cnbean.setBUp_Tbar(BUp_Tbar);
		cnbean.setBUp_Num(BUp_Num);
		cnbean.setBUp_Time(BUp_Time);
		
		System.out.println(BUp_Time+" "+BUp_Num);
		
		new BUpDao().Update(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('�޸ĳɹ���');window.location='BUpManager.action';</script>");
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

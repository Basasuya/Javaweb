package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class BReturnUpdateSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int BReturn_Id ;
    private String BReturn_Name ;
    private String BReturn_Type ;
    private String BReturn_Unit;
    private int BReturn_Cost;
    private int BReturn_Pro;
    private int BReturn_Num;
    private String BReturn_Time;

	public int getBReturn_Id() {
		return BReturn_Id;
	}

	public void setBReturn_Id(int gReturn_Id) {
		BReturn_Id = gReturn_Id;
	}

	public String getBReturn_Name() {
		return BReturn_Name;
	}
	
	public String getBReturn_Unit() {
		return BReturn_Unit;
	}

	public void setBReturn_Unit(String gReturn_Unit) {
		BReturn_Unit = gReturn_Unit;
	}

	public int getBReturn_Cost() {
		return BReturn_Cost;
	}

	public void setBReturn_Cost(int gReturn_Cost) {
		BReturn_Cost = gReturn_Cost;
	}

	public int getBReturn_Pro() {
		return BReturn_Pro;
	}

	public void setBReturn_Pro(int gReturn_Pro) {
		BReturn_Pro = gReturn_Pro;
	}

	public void setBReturn_Name(String gReturn_Name) {
		BReturn_Name = gReturn_Name;
	}

	public String getBReturn_Type() {
		return BReturn_Type;
	}

	public void setBReturn_Type(String gReturn_Type) {
		BReturn_Type = gReturn_Type;
	}

	public int getBReturn_Num() {
		return BReturn_Num;
	}

	public void setBReturn_Num(int gReturn_Num) {
		BReturn_Num = gReturn_Num;
	}

	public String getBReturn_Time() {
		return BReturn_Time;
	}

	public void setBReturn_Time(String gReturn_Time) {
		BReturn_Time = gReturn_Time;
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
		
		
		BReturnBean cnbean=new BReturnBean();
		cnbean=new BReturnDao().GetAllBean(BReturn_Id);
		
		cnbean.setBReturn_Name(BReturn_Name);
		cnbean.setBReturn_Type(BReturn_Type);
		cnbean.setBReturn_Unit(BReturn_Unit);
		cnbean.setBReturn_Cost(BReturn_Cost);
		cnbean.setBReturn_Pro(BReturn_Pro);
		cnbean.setBReturn_Num(BReturn_Num);
		cnbean.setBReturn_Time(BReturn_Time);
		
		System.out.println(BReturn_Time+" "+BReturn_Num);
		
		new BReturnDao().Update(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('�޸ĳɹ���');window.location='BReturnManager.action';</script>");
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

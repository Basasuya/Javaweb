package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class BStockUpdateSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int BStock_Id ;
    private String BStock_Name ;
    private String BStock_Type ;
    private String BStock_Unit;
    private int BStock_Cost;
    private int BStock_Pro;
    private int BStock_Num;
    private String BStock_Time;

	public int getBStock_Id() {
		return BStock_Id;
	}

	public void setBStock_Id(int gStock_Id) {
		BStock_Id = gStock_Id;
	}

	public String getBStock_Name() {
		return BStock_Name;
	}
	
	public String getBStock_Unit() {
		return BStock_Unit;
	}

	public void setBStock_Unit(String gStock_Unit) {
		BStock_Unit = gStock_Unit;
	}

	public int getBStock_Cost() {
		return BStock_Cost;
	}

	public void setBStock_Cost(int gStock_Cost) {
		BStock_Cost = gStock_Cost;
	}

	public int getBStock_Pro() {
		return BStock_Pro;
	}

	public void setBStock_Pro(int gStock_Pro) {
		BStock_Pro = gStock_Pro;
	}

	public void setBStock_Name(String gStock_Name) {
		BStock_Name = gStock_Name;
	}

	public String getBStock_Type() {
		return BStock_Type;
	}

	public void setBStock_Type(String gStock_Type) {
		BStock_Type = gStock_Type;
	}

	public int getBStock_Num() {
		return BStock_Num;
	}

	public void setBStock_Num(int gStock_Num) {
		BStock_Num = gStock_Num;
	}

	public String getBStock_Time() {
		return BStock_Time;
	}

	public void setBStock_Time(String gStock_Time) {
		BStock_Time = gStock_Time;
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
		
		
		BStockBean cnbean=new BStockBean();
		cnbean=new BStockDao().GetAllBean(BStock_Id);
		
		cnbean.setBStock_Name(BStock_Name);
		cnbean.setBStock_Type(BStock_Type);
		cnbean.setBStock_Unit(BStock_Unit);
		cnbean.setBStock_Cost(BStock_Cost);
		cnbean.setBStock_Pro(BStock_Pro);
		cnbean.setBStock_Num(BStock_Num);
		cnbean.setBStock_Time(BStock_Time);
		
		System.out.println(BStock_Time+" "+BStock_Num);
		
		new BStockDao().Update(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('�޸ĳɹ���');window.location='BStockManager.action';</script>");
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

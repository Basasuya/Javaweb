package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class BoodsUpdateSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int Boods_Id ;
    private String Boods_Name ;
    private String Boods_Type ;
    private String Boods_Unit;
    private int Boods_Price; 
    private int Boods_Warehouse;
    private int Boods_Bar;
	
   
	public int getBoods_Id() {
		return Boods_Id;
	}

	public void setBoods_Id(int goods_Id) {
		Boods_Id = goods_Id;
	}

	public String getBoods_Name() {
		return Boods_Name;
	}

	public void setBoods_Name(String goods_Name) {
		Boods_Name = goods_Name;
	}

	public String getBoods_Type() {
		return Boods_Type;
	}

	public void setBoods_Type(String goods_Type) {
		Boods_Type = goods_Type;
	}
	
	
	public String getBoods_Unit() {
		return Boods_Unit;
	}

	public void setBoods_Unit(String goods_Unit) {
		Boods_Unit = goods_Unit;
	}

	public int getBoods_Price() {
		return Boods_Price;
	}

	public void setBoods_Price(int goods_Price) {
		Boods_Price = goods_Price;
	}


	public int getBoods_Warehouse() {
		return Boods_Warehouse;
	}

	public void setBoods_Warehouse(int goods_Warehouse) {
		Boods_Warehouse = goods_Warehouse;
	}

	public int getBoods_Bar() {
		return Boods_Bar;
	}

	public void setBoods_Bar(int goods_Bar) {
		Boods_Bar = goods_Bar;
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
		
		
		BoodsBean cnbean=new BoodsBean();
		cnbean=new BoodsDao().GetAllBean(Boods_Id);
		
		cnbean.setBoods_Name(Boods_Name);
		cnbean.setBoods_Type(Boods_Type);
		cnbean.setBoods_Unit(Boods_Unit);
		cnbean.setBoods_Price(Boods_Price);
		cnbean.setBoods_Warehouse(Boods_Warehouse);
		cnbean.setBoods_Bar(Boods_Bar);
		
	//	System.out.println(Boods_Bar+" ");
		
		new BoodsDao().Update(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('�޸ĳɹ���');window.location='BoodsManager.action';</script>");
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

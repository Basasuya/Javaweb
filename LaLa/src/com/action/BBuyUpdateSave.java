package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class BBuyUpdateSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int BBuy_Id ;
    private String BBuy_Name ;
    private String BBuy_Type ;
    private String BBuy_Unit ;
    private int BBuy_Pro;
    private int BBuy_Num;
    private String BBuy_Time;

	public int getBBuy_Id() {
		return BBuy_Id;
	}

	public void setBBuy_Id(int gBuy_Id) {
		BBuy_Id = gBuy_Id;
	}

	public String getBBuy_Name() {
		return BBuy_Name;
	}

	public void setBBuy_Name(String gBuy_Name) {
		BBuy_Name = gBuy_Name;
	}

	public String getBBuy_Type() {
		return BBuy_Type;
	}

	public void setBBuy_Type(String gBuy_Type) {
		BBuy_Type = gBuy_Type;
	}

	public int getBBuy_Num() {
		return BBuy_Num;
	}
	
	public String getBBuy_Unit() {
		return BBuy_Unit;
	}

	public void setBBuy_Unit(String gBuy_Unit) {
		BBuy_Unit = gBuy_Unit;
	}

	public int getBBuy_Pro() {
		return BBuy_Pro;
	}

	public void setBBuy_Pro(int gBuy_Pro) {
		BBuy_Pro = gBuy_Pro;
	}

	public void setBBuy_Num(int gBuy_Num) {
		BBuy_Num = gBuy_Num;
	}

	public String getBBuy_Time() {
		return BBuy_Time;
	}

	public void setBBuy_Time(String gBuy_Time) {
		BBuy_Time = gBuy_Time;
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
		
		
		BBuyBean cnbean=new BBuyBean();
		cnbean=new BBuyDao().GetAllBean(BBuy_Id);

		
		cnbean.setBBuy_Name(BBuy_Name);
		cnbean.setBBuy_Type(BBuy_Type);
		cnbean.setBBuy_Unit(BBuy_Unit);
		cnbean.setBBuy_Pro(BBuy_Pro);
		cnbean.setBBuy_Num(BBuy_Num);
		cnbean.setBBuy_Time(BBuy_Time);
		
		System.out.println(BBuy_Time+" "+BBuy_Num);
		
		new BBuyDao().Update(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('�޸ĳɹ���');window.location='BBuyManager.action';</script>");
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

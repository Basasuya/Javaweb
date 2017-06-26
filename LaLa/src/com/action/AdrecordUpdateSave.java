package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class AdrecordUpdateSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int Adrecord_Id ;
    private String Adrecord_Username ;
    private String Adrecord_Time;


	public int getAdrecord_Id() {
		return Adrecord_Id;
	}

	public void setAdrecord_Id(int adrecord_Id) {
		Adrecord_Id = adrecord_Id;
	}

	public String getAdrecord_Username() {
		return Adrecord_Username;
	}

	public void setAdrecord_Username(String adrecord_Username) {
		Adrecord_Username = adrecord_Username;
	}

	public String getAdrecord_Time() {
		return Adrecord_Time;
	}

	public void setAdrecord_Time(String adrecord_Time) {
		Adrecord_Time = adrecord_Time;
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
		
		
		AdrecordBean cnbean=new AdrecordBean();
		cnbean=new AdrecordDao().GetAllBean(Adrecord_Id);
		
		cnbean.setAdrecord_Username(Adrecord_Username);
		cnbean.setAdrecord_Time(Adrecord_Time);
		
	//	System.out.println(Adrecord_Time+" "+Adrecord_Num);
		
		new AdrecordDao().Update(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('�޸ĳɹ���');window.location='AdrecordManager.action';</script>");
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

package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class GBuyUpdate extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private int GBuy_Id;
	private GBuyBean cnbean;
	public int getGBuy_Id() {
		return GBuy_Id;
	}

	public void setGBuy_Id(int GBuyId) {
		GBuy_Id = GBuyId;
	}

	public GBuyBean getCnbean() {
		return cnbean;
	}

	public void setCnbean(GBuyBean cnbean) {
		this.cnbean = cnbean;
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
		
		//��ѯ
		cnbean=new GBuyDao().GetAllBean(GBuy_Id);
		return SUCCESS;
		
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
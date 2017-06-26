package com.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class GUpAddSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
    private String GUp_Name ;
    private int GUp_Num ;

	public String getGUp_Name() {
		return GUp_Name;
	}

	public void setGUp_Name(String gUp_Name) {
		GUp_Name = gUp_Name;
	}

	public int getGUp_Num() {
		return GUp_Num;
	}

	public void setGUp_Num(int gUp_Num) {
		GUp_Num = gUp_Num;
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

		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('�����µ�¼��');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		
		//��ѯ�û����Ƿ����

		GoodsBean tmpbean = new GoodsDao(). GetAllFirstBean("Goods_Name='"+GUp_Name+"'");
		if(tmpbean.getGoods_Id() == 0) {
			out.print("<script language='javascript'>alert('�����ڸ���Ʒ');history.back(-1);</script>");
			out.flush();out.close();return null;
		}

		if(tmpbean.getGoods_Warehouse() < GUp_Num) {
			out.print("<script language='javascript'>alert('û����ô��������Ʒ');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		//���
		
		   
		tmpbean.setGoods_Bar(tmpbean.getGoods_Bar() + GUp_Num);
		tmpbean.setGoods_Warehouse(tmpbean.getGoods_Warehouse() - GUp_Num);
		new GoodsDao().Update(tmpbean);
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time = dateFormat.format( now ); 
		
		GUpBean cnbean=new GUpBean();
		cnbean.setGUp_Name(GUp_Name);
		cnbean.setGUp_Type(tmpbean.getGoods_Type());
		cnbean.setGUp_Unit(tmpbean.getGoods_Unit());
		cnbean.setGUp_Twarehouse(tmpbean.getGoods_Warehouse());
		cnbean.setGUp_Tbar(tmpbean.getGoods_Bar());
		cnbean.setGUp_Num(GUp_Num);
		cnbean.setGUp_Time(Time);
		
		new GUpDao().Add(cnbean);
		
		
		//��ת
		out.print("<script language='javascript'>alert('��ӳɹ���');window.location='GUpManager.action';</script>");
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

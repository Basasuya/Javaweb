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


public class GDownAddSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
    private String GDown_Name ;
    private int GDown_Num ;

	public String getGDown_Name() {
		return GDown_Name;
	}

	public void setGDown_Name(String gDown_Name) {
		GDown_Name = gDown_Name;
	}

	public int getGDown_Num() {
		return GDown_Num;
	}

	public void setGDown_Num(int gDown_Num) {
		GDown_Num = gDown_Num;
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

		GoodsBean tmpbean = new GoodsDao(). GetAllFirstBean("Goods_Name='"+GDown_Name+"'");
		if(tmpbean.getGoods_Id() == 0) {
			out.print("<script language='javascript'>alert('�����ڸ���Ʒ');history.back(-1);</script>");
			out.flush();out.close();return null;
		}

		if(tmpbean.getGoods_Bar() < GDown_Num) {
			out.print("<script language='javascript'>alert('û����ô��������Ʒ');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		//���
		System.out.println(GDown_Num);
		   
		tmpbean.setGoods_Bar(tmpbean.getGoods_Bar() - GDown_Num);
		tmpbean.setGoods_Warehouse(tmpbean.getGoods_Warehouse() + GDown_Num);
		new GoodsDao().Update(tmpbean);
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time = dateFormat.format( now ); 
		
		GDownBean cnbean=new GDownBean();
		cnbean.setGDown_Name(GDown_Name);
		cnbean.setGDown_Type(tmpbean.getGoods_Type());
		cnbean.setGDown_Unit(tmpbean.getGoods_Unit());
		cnbean.setGDown_Twarehouse(tmpbean.getGoods_Warehouse());
		cnbean.setGDown_Tbar(tmpbean.getGoods_Bar());
		cnbean.setGDown_Num(GDown_Num);
		cnbean.setGDown_Time(Time);
		
		new GDownDao().Add(cnbean);
		
		
		//��ת
		out.print("<script language='javascript'>alert('��ӳɹ���');window.location='GDownManager.action';</script>");
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

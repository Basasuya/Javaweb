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


public class BUpAddSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
    private String BUp_Name ;
    private int BUp_Num ;

	public String getBUp_Name() {
		return BUp_Name;
	}

	public void setBUp_Name(String gUp_Name) {
		BUp_Name = gUp_Name;
	}

	public int getBUp_Num() {
		return BUp_Num;
	}

	public void setBUp_Num(int gUp_Num) {
		BUp_Num = gUp_Num;
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

		BoodsBean tmpbean = new BoodsDao().GetAllFirstBean("Boods_Name='"+BUp_Name+"'");
		if(tmpbean.getBoods_Id() == 0) {
			out.print("<script language='javascript'>alert('�����ڸ�����');history.back(-1);</script>");
			out.flush();out.close();return null;
		}

		if(tmpbean.getBoods_Warehouse() < BUp_Num) {
			out.print("<script language='javascript'>alert('û����ô����������');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		//���
		
		   
		tmpbean.setBoods_Bar(tmpbean.getBoods_Bar() + BUp_Num);
		tmpbean.setBoods_Warehouse(tmpbean.getBoods_Warehouse() - BUp_Num);
		new BoodsDao().Update(tmpbean);
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time = dateFormat.format( now ); 
		
		BUpBean cnbean=new BUpBean();
		cnbean.setBUp_Name(BUp_Name);
		cnbean.setBUp_Type(tmpbean.getBoods_Type());
		cnbean.setBUp_Unit(tmpbean.getBoods_Unit());
		cnbean.setBUp_Twarehouse(tmpbean.getBoods_Warehouse());
		cnbean.setBUp_Tbar(tmpbean.getBoods_Bar());
		cnbean.setBUp_Num(BUp_Num);
		cnbean.setBUp_Time(Time);
		
		new BUpDao().Add(cnbean);
		
		
		//��ת
		out.print("<script language='javascript'>alert('��ӳɹ���');window.location='BUpManager.action';</script>");
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

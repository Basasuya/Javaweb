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


public class BDownAddSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
    private String BDown_Name ;
    private int BDown_Num ;

	public String getBDown_Name() {
		return BDown_Name;
	}

	public void setBDown_Name(String gDown_Name) {
		BDown_Name = gDown_Name;
	}

	public int getBDown_Num() {
		return BDown_Num;
	}

	public void setBDown_Num(int gDown_Num) {
		BDown_Num = gDown_Num;
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

		BoodsBean tmpbean = new BoodsDao(). GetAllFirstBean("Boods_Name='"+BDown_Name+"'");
		if(tmpbean.getBoods_Id() == 0) {
			out.print("<script language='javascript'>alert('�����ڸ�����');history.back(-1);</script>");
			out.flush();out.close();return null;
		}

		if(tmpbean.getBoods_Bar() < BDown_Num) {
			out.print("<script language='javascript'>alert('û����ô����������');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		//���
		System.out.println(BDown_Num);
		   
		tmpbean.setBoods_Bar(tmpbean.getBoods_Bar() - BDown_Num);
		tmpbean.setBoods_Warehouse(tmpbean.getBoods_Warehouse() + BDown_Num);
		new BoodsDao().Update(tmpbean);
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time = dateFormat.format( now ); 
		
		BDownBean cnbean=new BDownBean();
		cnbean.setBDown_Name(BDown_Name);
		cnbean.setBDown_Type(tmpbean.getBoods_Type());
		cnbean.setBDown_Unit(tmpbean.getBoods_Unit());
		cnbean.setBDown_Twarehouse(tmpbean.getBoods_Warehouse());
		cnbean.setBDown_Tbar(tmpbean.getBoods_Bar());
		cnbean.setBDown_Num(BDown_Num);
		cnbean.setBDown_Time(Time);
		
		new BDownDao().Add(cnbean);
		
		
		//��ת
		out.print("<script language='javascript'>alert('��ӳɹ���');window.location='BDownManager.action';</script>");
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

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


public class BBuyAddSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
    private String BBuy_Name ;
    private int BBuy_Num ;

	public String getBBuy_Name() {
		return BBuy_Name;
	}

	public void setBBuy_Name(String gUp_Name) {
		BBuy_Name = gUp_Name;
	}

	public int getBBuy_Num() {
		return BBuy_Num;
	}

	public void setBBuy_Num(int gUp_Num) {
		BBuy_Num = gUp_Num;
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

		BoodsBean tmpbean = new BoodsDao(). GetAllFirstBean("Boods_Name='"+BBuy_Name+"'");
		if(tmpbean.getBoods_Id() == 0) {
			out.print("<script language='javascript'>alert('�����ڸ�����');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		if(tmpbean.getBoods_Bar() - BBuy_Num < 0) {
			out.print("<script language='javascript'>alert('������������');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		   
		tmpbean.setBoods_Bar(tmpbean.getBoods_Bar() - BBuy_Num);
		new BoodsDao().Update(tmpbean);
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time = dateFormat.format( now ); 
		
		int tmp = tmpbean.getBoods_Price()* BBuy_Num;
		
		BBuyBean cnbean=new BBuyBean();
		cnbean.setBBuy_Name(BBuy_Name);
		cnbean.setBBuy_Type(tmpbean.getBoods_Type());
		cnbean.setBBuy_Unit(tmpbean.getBoods_Unit());
		cnbean.setBBuy_Pro(tmp);
		cnbean.setBBuy_Num(BBuy_Num);
		cnbean.setBBuy_Time(Time);
		
		new BBuyDao().Add(cnbean);
		
		int fl = Integer.parseInt(session.getAttribute("id").toString());
		AdminBean t2 = new AdminDao().GetAllBean(fl);
		ProfitBean t3 = new ProfitBean();
		t3.setProfit_Username(t2.getAdmin_Username());
		t3.setProfit_Time(Time);
		t3.setProfit_Gain(tmp);
		new ProfitDao().Add(t3);
		
		
		//��ת
		out.print("<script language='javascript'>alert('��ӳɹ���');window.location='BBuyManager.action';</script>");
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

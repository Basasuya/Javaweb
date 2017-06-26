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


public class BStockAddSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
    private String BStock_Name ;
    private int BStock_Cost;
    private int BStock_Num ;

	public String getBStock_Name() {
		return BStock_Name;
	}

	public void setBStock_Name(String gUp_Name) {
		BStock_Name = gUp_Name;
	}

	public int getBStock_Cost() {
		return BStock_Cost;
	}

	public void setBStock_Cost(int gStock_Cost) {
		BStock_Cost = gStock_Cost;
	}

	public int getBStock_Num() {
		return BStock_Num;
	}

	public void setBStock_Num(int gUp_Num) {
		BStock_Num = gUp_Num;
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

		BoodsBean tmpbean = new BoodsDao(). GetAllFirstBean("Boods_Name='"+BStock_Name+"'");
	//	System.out.println(tmpbean.getBoods_Bar());
		
		if(tmpbean.getBoods_Id() == 0) {
			out.print("<script language='javascript'>alert('�����ڸ�����');history.back(-1);</script>");
			out.flush();out.close();return null;
		}

		//���
		
		   
		tmpbean.setBoods_Warehouse(tmpbean.getBoods_Warehouse() + BStock_Num);
		new BoodsDao().Update(tmpbean);
		
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String Time = dateFormat.format( now ); 
		System.out.println("BStock_Cost = "+BStock_Cost);
		int tmp = -BStock_Cost* BStock_Num;
		BStockBean cnbean=new BStockBean();
		cnbean.setBStock_Name(BStock_Name);
		cnbean.setBStock_Type(tmpbean.getBoods_Type());
		cnbean.setBStock_Unit(tmpbean.getBoods_Unit());
		cnbean.setBStock_Cost(BStock_Cost);
		cnbean.setBStock_Pro(tmp);
		cnbean.setBStock_Num(BStock_Num);
		cnbean.setBStock_Time(Time);
		
		new BStockDao().Add(cnbean);
		
		int fl = Integer.parseInt(session.getAttribute("id").toString());
		AdminBean t2 = new AdminDao().GetAllBean(fl);
		ProfitBean t3 = new ProfitBean();
		t3.setProfit_Username(t2.getAdmin_Username());
		t3.setProfit_Time(Time);
		t3.setProfit_Gain(tmp);
		new ProfitDao().Add(t3);
		
		//��ת
		out.print("<script language='javascript'>alert('��ӳɹ���');window.location='BStockManager.action';</script>");
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

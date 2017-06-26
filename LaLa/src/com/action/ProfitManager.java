package com.action;

import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class ProfitManager extends ActionSupport {
//	private static Logger logger = Logger.getLogger(AdminManager.class);
	
	
	//������Action�����ڷ�װ�û��������������
	private List<ProfitBean> list;
	private String SearchRow;
	private String SearchKey;
	private List<UserBean> users;
	
	public List<ProfitBean> getList() {
		return list;
	}

	public void setList(List<ProfitBean> list) {
		this.list = list;
	}

	public String getSearchRow() {
		return SearchRow;
	}

	public void setSearchRow(String searchRow) {
		SearchRow = searchRow;
	}

	public String getSearchKey() {
		return SearchKey;
	}

	public void setSearchKey(String searchKey) {
		SearchKey = searchKey;
	}

	public List<UserBean> getUsers() {
		return users;
	}

	public void setUsers(List<UserBean> users) {
		this.users = users;
	}

	//�����û������execute����
	public String execute() throws Exception {
	//	logger.debug("start excute");
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
			out.print("<script language='javascript'>alert('请重新登陆！');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		
		//��ѯ����
		String strWhere="1=1";
		
		if(isInvalid(SearchKey)) {
			list=new ProfitDao().GetAllList(strWhere,"Profit_Id");
		}else {
		
		List<ProfitBean> tmplist = new ProfitDao().GetAllList(strWhere,"Profit_Id");
		list = new ArrayList<ProfitBean>();
		
		for(ProfitBean temp:tmplist) {
			int tmpnum = (temp.getProfit_Time().charAt(5)-'0')*10 + (temp.getProfit_Time().charAt(6)-'0');
			if( tmpnum == Integer.parseInt(SearchKey)){
				ProfitBean aa = new ProfitBean();
				aa.setProfit_Id(temp.getProfit_Id());
				aa.setProfit_Username(temp.getProfit_Username());
				aa.setProfit_Gain(temp.getProfit_Gain());
				aa.setProfit_Time(temp.getProfit_Time());
				System.out.println(aa.getProfit_Id() +" " + aa.getProfit_Username() + " " + aa.getProfit_Gain() + " "+aa.getProfit_Time());
				list.add(aa);
			}
		}
	
		}
		
		HashMap<String, Integer> mp = new HashMap<String, Integer>();
		for(ProfitBean temp:list) {
			if(mp.containsKey(temp.getProfit_Username())) {
				mp.put(temp.getProfit_Username(), temp.getProfit_Gain()+mp.get(temp.getProfit_Username()));
			}else {
				mp.put(temp.getProfit_Username(), temp.getProfit_Gain());
			}
		}
		
		
		users = new ArrayList<UserBean>();
		Iterator iter = mp.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Object key = entry.getKey();
			Object val = entry.getValue();
			String key1 = key.toString();
			Integer val1 = Integer.parseInt(val.toString());	
			
			UserBean tmp = new UserBean();
			tmp.setName(key1);
			tmp.setProfit(val1);
			users.add(tmp);
		}
		for(UserBean temp: users) {
			System.out.println(temp.getName()+" "+temp.getProfit());
		}
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

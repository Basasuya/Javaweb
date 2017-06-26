package com.action;
import java.util.*;
import java.text.SimpleDateFormat; 
import org.apache.log4j.Logger;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.dao.*;
import com.bean.*;

import com.opensymphony.xwork2.ActionSupport;

public class AdminManager extends ActionSupport {
	//下面是Action内用于封装用户请求参数的属性
	private String Username;
	private String Password;
	private String Msg;

	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	//处理用户请求的execute方法
	public String execute() throws Exception {
		if (0 == new AdminDao().CheckLogin(Username, Password)) {
			Msg = "用户名或者密码错误";
			return INPUT;
		}
		else
		{
			Msg = null;
			
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String Time = dateFormat.format( now ); 
			
			
			AdminBean cnbean = new AdminDao().GetAllFirstBean(Username);
			
			AdrecordBean tmpbean = new AdrecordBean();
			tmpbean.setAdrecord_Username(Username);
			tmpbean.setAdrecord_Time(Time);
			System.out.println("hi" + Username);
			
			new AdrecordDao().Add(tmpbean);
			
			HttpSession session = ServletActionContext.getRequest().getSession();
			session.setAttribute("id", cnbean.getAdmin_Id());
			return SUCCESS;
		}
	}
}
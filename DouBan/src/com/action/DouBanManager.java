package com.action;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class DouBanManager extends ActionSupport {
	private List<DouBanBean> list;
	private String MovieKey;
	private String KeyWords;
	private String UpEdge;
	private String DownEdge;
	private String Years;
	private String DonHave;
	
	public String getDonHave() {
		return DonHave;
	}

	public void setDonHave(String donHave) {
		DonHave = donHave;
	}

	public String getYears() {
		return Years;
	}

	public void setYears(String years) {
		Years = years;
	}

	public List<DouBanBean> getList() {
		return list;
	}
	
	public void setList(List<DouBanBean> list) {
		this.list = list;
	}

	public String getMovieKey() {
		return MovieKey;
	}

	public void setMovieKey(String movieKey) {
		MovieKey = movieKey;
	}

	public String getKeyWords() {
		return KeyWords;
	}

	public void setKeyWords(String keyWords) {
		KeyWords = keyWords;
	}
	
	
	
	public String getUpEdge() {
		return UpEdge;
	}
	public void setUpEdge(String upEdge) {
		UpEdge = upEdge;
	}
	public String getDownEdge() {
		return DownEdge;
	}
	public void setDownEdge(String downEdge) {
		DownEdge = downEdge;
	}
	public String execute() throws Exception {
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
	//	List<DouBanBean> list2=new DouBanDao().GetAllList();
		
//		ObjectOutputStream ou = new ObjectOutputStream(new FileOutputStream("E:/data.dat"));
//		ou.writeObject(list2);
//		ou.close();
		System.out.println("write ok");
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("E:/data.dat"));
		List<DouBanBean> list3 = new ArrayList<DouBanBean>();
		list3 = (ArrayList<DouBanBean>)in.readObject();
 		
		
		list = new ArrayList<DouBanBean>();
		System.out.println("hhhh");
		
	//	System.out.println("hhh "+ UpEdge+" "+DownEdge);
		if(UpEdge == null || UpEdge.length() == 0) UpEdge = "0";
		if(DownEdge == null || DownEdge.length() == 0) DownEdge = "10";
		System.out.println(UpEdge+" "+DownEdge);
		
		int cnt = 0;
		int Up = Integer.parseInt(UpEdge);
		int Down = Integer.parseInt(DownEdge);
		System.out.println(Up+" "+Down);
		String prename = "";
		
		for(DouBanBean tmp:list3) {
			String t1 = tmp.getMoivename();
			if(prename.equals(t1) == true) continue;
			prename = t1;
		//	System.out.println(t1);
			if(MovieKey != null && MovieKey.length() > 0 && t1.contains(MovieKey) == false) continue;
			if(DonHave != null && DonHave.length() > 0 && t1.contains(DonHave) == true) continue;
			String t2 = tmp.getStars();
			if(KeyWords != null && KeyWords.length() > 0 && t2.contains(KeyWords) == false) continue;
			int t4 = tmp.getYear();
			if(Years != null && Years.length() > 0 &&  Integer.parseInt(Years) != t4) continue;
			
			int t3 = tmp.getShows().charAt(0) - '0';
			if(t3 < Up || t3 >= Down) continue;
			
			DouBanBean tmp2 = new DouBanBean();
			tmp2.setMoivename(tmp.getMoivename());
			tmp2.setNum(tmp.getNum());
			tmp2.setShows(tmp.getShows());
			tmp2.setStars(tmp.getStars());
			tmp2.setYear(tmp.getYear());
			
			list.add(tmp2);
			
			cnt ++;
			if(cnt > 20) break;
		}
		for(DouBanBean tmp:list) {
			System.out.println(tmp.getMoivename()+" "+tmp.getNum());
		}
		return SUCCESS;
	}
	
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
	
}

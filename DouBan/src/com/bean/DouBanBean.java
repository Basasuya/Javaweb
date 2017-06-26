package com.bean;

import java.io.Serializable;

public class DouBanBean implements Serializable{
	private int year;
	private String num;
	private String moivename;
	private String stars;
	private String shows;
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getMoivename() {
		return moivename;
	}
	public void setMoivename(String moivename) {
		this.moivename = moivename;
	}
	public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getShows() {
		return shows;
	}
	public void setShows(String shows) {
		this.shows = shows;
	}
	
}

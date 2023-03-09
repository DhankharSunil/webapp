package com.web.application;

public class IplData {
	public String date;
	public String time;
	public String match;
	public String vsmatch;
	public String cityPlace;
	public String userMail;
	
	
	
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getVsmatch() {
		return vsmatch;
	}
	public void setVsmatch(String vsmatch) {
		this.vsmatch = vsmatch;
	}
	public String getCityPlace() {
		return cityPlace;
	}
	public void setCityPlace(String cityPlace) {
		this.cityPlace = cityPlace;
	}
	public String yearFrom;
	
	
	public String getYearFrom() {
		return yearFrom;
	}
	public void setYearFrom(String yearFrom) {
		this.yearFrom = yearFrom;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	@Override
	public String toString() {
		return "IplData [date=" + date + ", time=" + time + ", match=" + match + ", vsmatch=" + vsmatch + ", cityPlace="
				+ cityPlace + ", userMail=" + userMail + ", yearFrom=" + yearFrom + "]";
	}
}
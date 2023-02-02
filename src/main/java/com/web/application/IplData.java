package com.web.application;

public class IplData {
	public String date;
	public String time;
	public String match;
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
		return "IplData [date=" + date + ", time=" + time + ", match=" + match + ", yearFrom=" + yearFrom + "]";
	}
}
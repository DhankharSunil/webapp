package com.web.application;

public class UserLoginRegister {
	
	String fname;
	String lname;
	String uname;
	String pass;
	String copass;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getCopass() {
		return copass;
	}
	public void setCopass(String copass) {
		this.copass = copass;
	}
	@Override
	public String toString() {
		return "UserLoginRegister [fname=" + fname + ", lname=" + lname + ", uname=" + uname + ", pass=" + pass
				+ ", copass=" + copass + "]";
	}
	
	

}

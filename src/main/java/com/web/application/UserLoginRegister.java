package com.web.application;

public class UserLoginRegister {
	
	String fname;
	String lname;
	String uname;
	String pass;
	String copass;
	String mobileNo;
	String state;
	String district;
	String RESET_TOKEN;
	String flag;
	
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRESET_TOKEN() {
		return RESET_TOKEN;
	}
	public void setRESET_TOKEN(String rESET_TOKEN) {
		RESET_TOKEN = rESET_TOKEN;
	}
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
				+ ", copass=" + copass + ", mobileNo=" + mobileNo + ", state=" + state + ", district=" + district
				+ ", RESET_TOKEN=" + RESET_TOKEN + ", flag=" + flag + "]";
	}
	
	

}

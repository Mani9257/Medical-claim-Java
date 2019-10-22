package com.claim.medical.dto;

public class LoginRequestDto {
	
	private String adminName;
	private String password;
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginRequestDto [adminName=" + adminName + ", password=" + password + "]";
	}
	
	
	
	
	

}

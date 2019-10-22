package com.claim.medical.dto;

public class LoginResponseDto {

	private String adminName;
	private Integer adminId;
	private String message;
	private Integer statusCode;
	
	@Override
	public String toString() {
		return "LoginResponseDto [adminName=" + adminName + ", adminId=" + adminId + ", message=" + message
				+ ", statusCode=" + statusCode + "]";
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
	
	
}

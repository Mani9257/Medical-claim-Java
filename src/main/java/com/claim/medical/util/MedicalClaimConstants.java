package com.claim.medical.util;

import org.springframework.http.HttpStatus;

public class MedicalClaimConstants {
	private MedicalClaimConstants() {
		
	}
	public static final String  SUCCESSFUL_MESSAGE="Sucessful";
	public static final Integer  STATUS_CODE_FOR_OK=HttpStatus.OK.value();
	public static final String FAILURE_MESSAGE = "Invalid Credentials";
	
	public static final String  APPROVED_STATUS_BY_ADMIN="APPROVED";
	public static final String  REJECTED_STATUS_BY_ADMIN="REJECTED";
	public static final String  PENDING_STATUS_BY_ADMIN="PENDING";
	public static final String  NOTAPPLICABLE_STATUS_BY_ADMIN="N/A";
}

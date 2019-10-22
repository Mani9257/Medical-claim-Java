package com.claim.medical.util;


import org.springframework.stereotype.Component;

@Component
public class MedicalClaimConstants {
	private MedicalClaimConstants() {
		
	}

	public static final Integer SUCCESS_STATUS_CODE = 200;
	public static final String SUCCESS_STATUS_MESSAGE_POLICY_FETCH = "Successfully fetch policy details";
	public static final String EXPIRED_STATUS_MESSAGE_POLICY_FETCH = "Policy Expired connect to system admin";
	public static final String INVALIED_STATUS_MESSAGE_POLICY_FETCH = "Please enter a valid policy";
	public static final Integer NOT_FOUND = 500;
	

}

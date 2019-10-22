package com.claim.medical.util;


import org.springframework.stereotype.Component;

@Component
public class MedicalClaimConstants {
<<<<<<< HEAD
=======
	private MedicalClaimConstants() {
		
	}

	public static final Integer SUCCESS_STATUS_CODE = 200;
	public static final String SUCCESS_STATUS_MESSAGE_POLICY_FETCH = "Successfully fetch policy details";
	public static final String EXPIRED_STATUS_MESSAGE_POLICY_FETCH = "Policy Expired connect to system admin";
	public static final String INVALIED_STATUS_MESSAGE_POLICY_FETCH = "Please enter a valid policy";
	public static final Integer NOT_FOUND = 500;
>>>>>>> 29fd5d29ccfc16664b09e2e407fda06b670ea568
	

	private MedicalClaimConstants() {
		super();
	}
	public static final String SUCCESS_MESSAGE = "login succeessful";
	public static final Integer SUCCESS_CODE = 200;

	public static final String ERROR_MESSAGE = "user name or password is incorrect";
	public static final Integer ERROR_CODE = 400;
}


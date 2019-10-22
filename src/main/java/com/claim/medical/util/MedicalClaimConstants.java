package com.claim.medical.util;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MedicalClaimConstants {
	private MedicalClaimConstants() {
		super();
	}

	public static final Integer SUCCESS_STATUS_CODE = 200;
	public static final String SUCCESS_STATUS_MESSAGE_POLICY_FETCH = "Successfully fetch policy details";
	public static final String EXPIRED_STATUS_MESSAGE_POLICY_FETCH = "Policy Expired connect to system admin";
	public static final String INVALIED_STATUS_MESSAGE_POLICY_FETCH = "Please enter a valid policy";
	public static final Integer NOT_FOUND = 500;
	public static final String PENDING = "Pending";
	public static final String FAILED_TO_SAVE_CLAIM_STATUS = "Failed to save claim status. Please contact system admin";
	public static final String FAILED_TO_SAVE_MEDICAL_CLAIM = "Failed to save medical claim.Please contact system admin";
	public static final String MEDICAL_CLAIM_SAVED = "Medical Claim saved for the user";
	public static final String ADMIN_CREDENTIALS_NOT_VALID = "Admin is not registerd to the portal";
	public static final String FIRST_LEVEL_APPROVER = "Approver";
	public static final String SECOND_LEVEL_APPROVER = "Senior Approver";
	public static final String APPROVED = "Approved";
	public static final String POLICY_NOT_FOUND = "No such policy type exists";
	public static final String USER_POLICY_NOT_FOUND = "No such policy type exists for the user";
	public static final String USER_NOT_FOUND = "No such user exists";
	public static final String LIST_OF_MEDICAL_CLAIMS_FOUND = "List of pending medical claims found";
	public static final Object BOOLEAN_TRUE = "true";
	public static final String UPDATED_CLAIM_STATUS_SUCCESSFULLY = "Claim Status has been updated successfully";
	public static final String CLAIM_STATUS_UPDATION_FAILED = "Claim Status has been updated successfully";
	public static final String SUCCESS_MESSAGE = "login succeessful";
	public static final Integer SUCCESS_CODE = 200;
	public static final String ERROR_MESSAGE = "user name or password is incorrect";
	public static final Integer ERROR_CODE = 400;
	public static final String FAILURE_STATUS = "Failure";
	public static final String SUCCESS_STATUS = "Success";

	public static final String SUCCESSFUL_MESSAGE = "Sucessful";
	public static final Integer STATUS_CODE_FOR_OK = HttpStatus.OK.value();
	public static final String FAILURE_MESSAGE = "Invalid Credentials";

	public static final String APPROVED_STATUS_BY_ADMIN = "APPROVED";
	public static final String REJECTED_STATUS_BY_ADMIN = "REJECTED";
	public static final String PENDING_STATUS_BY_ADMIN = "PENDING";
}

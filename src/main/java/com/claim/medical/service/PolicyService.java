package com.claim.medical.service;

import com.claim.medical.exception.MedicalClaimException;

public interface PolicyService {

	String getPolicyNameById(Integer policyId) throws MedicalClaimException;

}

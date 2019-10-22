package com.claim.medical.service;

import java.util.Optional;

import com.claim.medical.entity.Policy;
import com.claim.medical.exception.MedicalClaimException;

/**
 * this policy Service will return policy based on policyId
 * 
 * @author Abhishek, Shreya E Nair
 *
 */
public interface PolicyService {
	Optional<Policy> getPolicy(Integer policyId);
	
	String getPolicyNameById(Integer policyId) throws MedicalClaimException;

}

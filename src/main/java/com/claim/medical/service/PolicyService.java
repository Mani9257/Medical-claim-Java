package com.claim.medical.service;

import java.util.List;

import com.claim.medical.dto.PolicyResponseDTO;

/**
 * this policy Service will return policy based on policyId
 * 
 * @author Abhishek
 *
 */
public interface PolicyService {

	List<PolicyResponseDTO> getAllPolicies();

}

package com.claim.medical.service;

import java.util.List;
import java.util.Optional;

import com.claim.medical.dto.PolicyResponseDTO;
import com.claim.medical.entity.Policy;

/**
 * this policy Service will return policy based on policyId
 * 
 * @author Abhishek
 *
 */
public interface PolicyService {

	List<PolicyResponseDTO> getAllPolicies();



}

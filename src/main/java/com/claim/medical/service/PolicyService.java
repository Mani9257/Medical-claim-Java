package com.claim.medical.service;

import java.util.Optional;

import com.claim.medical.entity.Policy;

/**
 * this policy Service will return policy based on policyId
 * 
 * @author Abhishek
 *
 */
public interface PolicyService {
	Optional<Policy> getPolicy(Integer policyId);

}

package com.claim.medical.service;

import com.claim.medical.dto.ClaimDetailsResponseDto;
import com.claim.medical.dto.PolicyCheckResponseDto;
import com.claim.medical.exception.PolicyExpiredException;
import com.claim.medical.exception.PolicyNotFoundException;

public interface PolicyCheckService {

	PolicyCheckResponseDto checkPolicy(Integer policyId) throws PolicyExpiredException, PolicyNotFoundException;

	ClaimDetailsResponseDto viewClaimStatus(Integer policyId) throws PolicyNotFoundException;

}

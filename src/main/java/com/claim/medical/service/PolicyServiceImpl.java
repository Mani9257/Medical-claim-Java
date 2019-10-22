package com.claim.medical.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.entity.Policy;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.PolicyRepository;
import com.claim.medical.util.MedicalClaimConstants;

/**
 * This class is used to fetch policy based on policyId passed
 * 
 * @author Shreya E Nair
 * @since 2019-10-22
 */
@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;

	/**
	 * This method is used to fetch policyd details based on policyId passed
	 * 
	 * @param policyId 
	 * @return Policy object
	 * @throws MedicalClaimException
	 */
	@Override
	public String getPolicyNameById(Integer policyId) throws MedicalClaimException {
		Optional<Policy> policy = policyRepository.findById(policyId);
		if (policy.isPresent()) {
			return policy.get().getPolicyType();
		} else {
			throw new MedicalClaimException(MedicalClaimConstants.POLICY_NOT_FOUND);
		}
	}

}

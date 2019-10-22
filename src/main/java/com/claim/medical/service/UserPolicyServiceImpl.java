package com.claim.medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.entity.UserPolicy;
import com.claim.medical.repository.UserPolicyRepository;

/**
 * this service will query to DB and return userPolicy based on policyId
 * 
 * @author Abhishek
 *
 */
@Service
public class UserPolicyServiceImpl implements UserPolicyService {

	/**
	 * this userPolicyRepository will query to DB
	 */
	@Autowired
	private UserPolicyRepository userPolicyRepository;

	/**
	 * this getUserPolicy will query to DB
	 * 
	 * @return UserPolicy
	 */
	@Override
	public UserPolicy getUserPolicy(Integer policyId) {
		return userPolicyRepository.findByPolicyId(policyId);
	}

}

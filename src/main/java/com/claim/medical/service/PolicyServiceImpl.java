package com.claim.medical.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.entity.Policy;
import com.claim.medical.repository.PolicyRepository;

/**
 * this service will query to DB
 * 
 * @author Abhishek
 *
 */
@Service
public class PolicyServiceImpl implements PolicyService {

	/**
	 * this PolicyRepository will query to DB to get policy details
	 */
	@Autowired
	private PolicyRepository policyRepository;

	/**
	 * this getPolicy will query to DB based on policyId
	 * 
	 * @return Optional<Policy>
	 */
	@Override
	public Optional<Policy> getPolicy(Integer policyId) {
		return policyRepository.findById(policyId);
	}

}

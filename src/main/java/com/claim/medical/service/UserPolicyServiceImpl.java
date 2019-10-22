package com.claim.medical.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.entity.UserPolicy;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.UserPolicyRepository;
import com.claim.medical.util.MedicalClaimConstants;

/**
 * This class is has 2 methods which does the following:
 * a) fetch eligible amount for a user for the policy he/she has applied based on user id passed
 * b) to fetch all the policies from database
 * 
 * @author Shreya E Nair
 * @since 2019-10-22
 */
@Service
public class UserPolicyServiceImpl implements UserPolicyService {

	@Autowired
	UserPolicyRepository userPolicyRepository;

	/**
	 * This method is used to fetch eligible amount for a user for the policy he/she has applied based on user id passed
	 * 
	 * @param userId 
	 * @return Eligible Amount
	 * @throws MedicalClaimException
	 */
	@Override
	public Double getEligibileAmount(Integer userId) throws MedicalClaimException {
		Optional<UserPolicy> userPolicy = userPolicyRepository.findByUserId(userId);
		if (userPolicy.isPresent()) {
			return userPolicy.get().getEligibilityAmount();
		} else {
			throw new MedicalClaimException(MedicalClaimConstants.USER_POLICY_NOT_FOUND);
		}

	}

	/**
	 * This method is used to fetch all the policies from database
	 * 
	 * @return Policy List	 
	 */
	@Override
	public List<UserPolicy> getAll() {
		return userPolicyRepository.findAll();
	}

}

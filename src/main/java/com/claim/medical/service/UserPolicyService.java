package com.claim.medical.service;
import java.util.List;

import com.claim.medical.entity.UserPolicy;
import com.claim.medical.exception.MedicalClaimException;


/**
 * @author Abhishek, Shreya E Nair
 *
 */
public interface UserPolicyService {

	Double getEligibileAmount(Integer userId) throws MedicalClaimException;

	List<UserPolicy> getAll();

	UserPolicy getUserPolicy(Integer policyId);
}

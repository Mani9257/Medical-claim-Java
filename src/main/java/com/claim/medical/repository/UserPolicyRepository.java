package com.claim.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.medical.entity.UserPolicy;

/**
 * this repository will get user policy details
 * 
 * @author Abhishek
 *
 */
public interface UserPolicyRepository extends JpaRepository<UserPolicy, Integer> {

	UserPolicy findByPolicyId(Integer policyId);

}

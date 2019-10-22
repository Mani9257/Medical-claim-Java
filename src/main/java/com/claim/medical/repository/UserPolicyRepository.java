package com.claim.medical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.UserPolicy;

/**
 * this repository will get user policy details
 * 
 * @author Abhishek
 *
 */
@Repository
public interface UserPolicyRepository extends JpaRepository<UserPolicy, Integer> {
	UserPolicy findByPolicyId(Integer policyId);
	Optional<UserPolicy> findByUserId(Integer userId);
	
}

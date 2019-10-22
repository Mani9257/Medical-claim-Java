package com.claim.medical.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.Policy;
import com.claim.medical.entity.UserPolicy;



@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	Policy findByPolicyId(Integer policyId);
	
	
	
}

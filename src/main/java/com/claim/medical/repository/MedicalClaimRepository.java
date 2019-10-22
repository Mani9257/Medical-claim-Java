package com.claim.medical.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.MedicalClaim;



@Repository
public interface MedicalClaimRepository extends JpaRepository<MedicalClaim, Integer> {

	MedicalClaim findByPolicyId(Integer policyId);
	
	
	
}

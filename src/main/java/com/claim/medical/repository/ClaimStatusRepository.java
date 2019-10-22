package com.claim.medical.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.ClaimStatus;



@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatus, Integer> {

	ClaimStatus findByClaimId(Integer claimId);
	
	
	
}

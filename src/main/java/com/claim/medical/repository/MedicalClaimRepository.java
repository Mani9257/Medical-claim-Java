package com.claim.medical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.MedicalClaim;

/**
 * this repository will get medical claim details
 * 
 * @author Abhishek
 *
 */
@Repository
public interface MedicalClaimRepository extends JpaRepository<MedicalClaim, Integer> {
	List<MedicalClaim> findMadicalClaimByClaimId(Integer claimId);
	MedicalClaim findByPolicyId(Integer policyId);
	

}

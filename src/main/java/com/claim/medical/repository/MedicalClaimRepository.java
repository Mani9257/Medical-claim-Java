package com.claim.medical.repository;

<<<<<<< HEAD
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.medical.entity.MedicalClaim;

/**
 * this repository will get medical claim details
 * 
 * @author Abhishek
 *
 */
public interface MedicalClaimRepository extends JpaRepository<MedicalClaim, Integer> {

	List<MedicalClaim> findMadicalClaimByClaimId(Integer claimId);

=======


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.MedicalClaim;



@Repository
public interface MedicalClaimRepository extends JpaRepository<MedicalClaim, Integer> {

	MedicalClaim findByPolicyId(Integer policyId);
	
	
	
>>>>>>> 29fd5d29ccfc16664b09e2e407fda06b670ea568
}

package com.claim.medical.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.medical.entity.ClaimStatus;

/**
 * this repository will query to DB and create Dynamic query for to fetch data
 * from DB
 * 
 * @author Abhishek
 *
 */
public interface ClaimStatusRepository extends JpaRepository<ClaimStatus, Integer> {

=======


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.ClaimStatus;



@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatus, Integer> {

	ClaimStatus findByClaimId(Integer claimId);
	
	
	
>>>>>>> 29fd5d29ccfc16664b09e2e407fda06b670ea568
}

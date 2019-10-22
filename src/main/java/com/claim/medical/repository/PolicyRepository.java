package com.claim.medical.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.medical.entity.Policy;

/**
 * this repository will get policy details
 * 
 * @author Abhishek
 *
 */
public interface PolicyRepository extends JpaRepository<Policy,Integer>{
	

=======


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.Policy;
import com.claim.medical.entity.UserPolicy;



@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	Policy findByPolicyId(Integer policyId);
	
	
	
>>>>>>> 29fd5d29ccfc16664b09e2e407fda06b670ea568
}

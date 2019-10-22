package com.claim.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.medical.entity.Policy;

/**
 * this repository will get policy details
 * 
 * @author Abhishek
 *
 */
public interface PolicyRepository extends JpaRepository<Policy,Integer>{
	

}

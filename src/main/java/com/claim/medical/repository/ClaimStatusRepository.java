package com.claim.medical.repository;

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

}

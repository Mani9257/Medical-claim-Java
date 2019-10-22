package com.claim.medical.service;

import java.util.List;

import com.claim.medical.entity.ClaimStatus;

/**
 * this ClaimStatusService will query to db to get claimStatus details
 * 
 * @author Abhishek
 *
 */
public interface ClaimStatusService {

	List<ClaimStatus> getAllClaims();

}

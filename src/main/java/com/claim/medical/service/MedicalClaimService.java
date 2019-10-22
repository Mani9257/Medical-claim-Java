package com.claim.medical.service;

import java.util.List;

import com.claim.medical.entity.MedicalClaim;

/**
 * @author Abhishek
 *
 */
public interface MedicalClaimService {
	
	List<MedicalClaim> getAllPendingMedicalClaims(Integer claimId);
	
	
	

}

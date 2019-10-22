package com.claim.medical.service;

import java.util.List;
import java.util.Optional;
import com.claim.medical.entity.Admin;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.exception.MedicalClaimException;

/**
 * this ClaimStatusService will query to db to get claimStatus details
 * 
 * @author Abhishek
 *
 */

public interface ClaimStatusService {

	public Optional<ClaimStatus> saveClaimStatus(MedicalClaim savedClaimRequest) throws MedicalClaimException;

	public List<ClaimStatus> getAllByFirstLevelClaimStatus();

	public List<ClaimStatus> getAllBySecondLevelClaimStatus();

	public boolean updateClaimStatusBasedOnApprover(Admin admin, Integer claimId, String approverStatus);
	
	List<ClaimStatus> getAllClaims();


}

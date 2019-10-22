package com.claim.medical.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.claim.medical.entity.Admin;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.ClaimStatusRepository;
import com.claim.medical.util.MedicalClaimConstants;

/**
* This class will has 3 methods which does the following:
* a)Save the claim status of a medical claim
* b)fetch medical claims pending for first level approval
* c)fetch medical claims pending at second level approval
* 
*  @author  Shreya E Nair, Abhishek
* @since   2019-10-22 
*/

/**
 * this service will return ClaimStatus details and communicate with
 * ClaimStatusRepository
 * 
 * @author Abhishek
 *
 */
@Service
public class ClaimStatusServiceImpl implements ClaimStatusService {

	@Autowired
	ClaimStatusRepository claimStatusRepository;
	private static final Logger lOGGER = LoggerFactory.getLogger(ClaimStatusServiceImpl.class);
	

	/**
	 * This method will save the claim status of a medical claim raised.
	 * 
	 * @param MedicalClaim object
	 * @return ClaimStatus object
	 * @throws MedicalClaimException
	 */
	@Override
	public Optional<ClaimStatus> saveClaimStatus(MedicalClaim savedClaimRequest) throws MedicalClaimException {
		ClaimStatus claimStatus = new ClaimStatus();
		try {
			claimStatus.setClaimId(savedClaimRequest.getClaimId());
			claimStatus.setFirstLevelClaimStatus(MedicalClaimConstants.PENDING);
			claimStatus.setSecondLevelClaimStatus(MedicalClaimConstants.PENDING);
			ClaimStatus savedClaimStatus = claimStatusRepository.save(claimStatus);
			lOGGER.info("Medical CLaim saved");
			Optional<ClaimStatus> claimStatusSaved = Optional.of(savedClaimStatus);
			return claimStatusSaved;
		} catch (Exception e) {
			throw new MedicalClaimException(MedicalClaimConstants.FAILED_TO_SAVE_CLAIM_STATUS);
		}
	}

	/**
	 * This method will fetch all medical claims pending at first level approval.
	 * 
	 * @return ClaimStatus List
	 */
	@Override
	public List<ClaimStatus> getAllByFirstLevelClaimStatus() {
		List<ClaimStatus> firstLevelClaimList = claimStatusRepository
				.findAllByFirstLevelClaimStatus(MedicalClaimConstants.PENDING);
		lOGGER.info("Medical CLaim list fetched for pending claims by first level approver");
		return firstLevelClaimList;
	}

	/**
	 * This method will fetch all medical claims approved at first level pending at second level approval
	 * 
	 * @return ClaimStatus List
	 */
	@Override
	public List<ClaimStatus> getAllBySecondLevelClaimStatus() {
		List<ClaimStatus> secondLevelClaimStatus = claimStatusRepository
				.findAllByFirstLevelClaimStatusAndSecondLevelClaimStatus(MedicalClaimConstants.APPROVED,
						MedicalClaimConstants.PENDING);
		lOGGER.info("Medical CLaim list fetched for pending claims by second level approved");
		return secondLevelClaimStatus;
	}

	@Override
	public boolean updateClaimStatusBasedOnApprover(Admin adminDetails, Integer claimId, String approverStatus) {
		ClaimStatus claimStatus = claimStatusRepository.findByClaimId(claimId);
		if (adminDetails.getRole().equalsIgnoreCase(MedicalClaimConstants.FIRST_LEVEL_APPROVER)) {
			claimStatus.setApproverId(adminDetails.getAdminId());
			claimStatus.setFirstLevelClaimStatus(MedicalClaimConstants.APPROVED);
			claimStatus.setSecondLevelClaimStatus(MedicalClaimConstants.PENDING);
		} else if (adminDetails.getRole().equalsIgnoreCase(MedicalClaimConstants.SECOND_LEVEL_APPROVER)) {
			claimStatus.setSeniorApproverId(adminDetails.getAdminId());
			claimStatus.setSecondLevelClaimStatus(MedicalClaimConstants.APPROVED);

		}
		claimStatusRepository.save(claimStatus);
		lOGGER.info("Medical CLaim updated after approval by ={}",adminDetails.getRole());
		return true;
	}
	
	
	/**
	 * this method will return all claims
	 */
	@Override
	public List<ClaimStatus> getAllClaims() {
		return claimStatusRepository.findAll();

	}

}

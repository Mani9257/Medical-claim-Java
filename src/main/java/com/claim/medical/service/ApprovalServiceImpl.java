package com.claim.medical.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.dto.ApprovalResponseDTO;
import com.claim.medical.entity.Admin;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.entity.UserPolicy;
import com.claim.medical.exception.AdminNotFoundException;
import com.claim.medical.repository.AdminRepository;
import com.claim.medical.repository.ClaimStatusRepository;
import com.claim.medical.repository.MedicalCalimRepository;
import com.claim.medical.repository.UserPolicyRepository;
import com.claim.medical.util.MedicalClaimConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * This Service Class is use to approve or reject the claim request raised by
 * patients
 * 
 * @author Ajith
 *
 */
@Service
@Slf4j
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	ClaimStatusRepository claimStatusRepository;

	@Autowired
	AdminRepository adminRepository;

	@Autowired
	MedicalCalimRepository medicalCalimRepository;

	@Autowired
	UserPolicyRepository userPolicyRepository;

	/**
	 * This Service is use to approve or reject the claim request raised by patient
	 * 
	 * @pathVariable Integer
	 * @pathVariable Integer
	 * @pathVariable String
	 * @return ApprovalResponseDTO
	 */
	public ApprovalResponseDTO adminApproval(Integer claimId, Integer approverId, String claimStatus)
			throws AdminNotFoundException {

		ApprovalResponseDTO approvalResponseDTO = new ApprovalResponseDTO();
		MedicalClaim mediClaim = medicalCalimRepository.findByClaimId(claimId);

		Optional<UserPolicy> userPolicy = userPolicyRepository.findByPolicyId(mediClaim.getPolicyId());

		Optional<Admin> admin = adminRepository.findByAdminId(approverId);

		Optional<ClaimStatus> claimRequest = claimStatusRepository.findByClaimIdAndApproverId(claimId, approverId);
		Optional<ClaimStatus> seniorApproverrequest = claimStatusRepository.findByClaimIdAndSeniorApproverId(claimId,
				approverId);

		
		if (admin.isPresent()) {
			log.info("Admin ={}", admin.get().getAdminName());

			if (claimRequest.isPresent() && admin.get().getAdminId().equals(claimRequest.get().getApproverId()) && userPolicy.isPresent()  ) {

				if (claimStatus.equalsIgnoreCase(MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN) && userPolicy.get().getEligibilityAmount() > mediClaim.getClaimAmount() ) {
					claimRequest.get().setFirstLevelClaimStatus(claimStatus);
					claimRequest.get().setSecondLevelClaimStatus(MedicalClaimConstants.NOTAPPLICABLE_STATUS_BY_ADMIN);
					claimStatusRepository.save(claimRequest.get());

				}
				else if(claimStatus.equalsIgnoreCase(MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN) && userPolicy.get().getEligibilityAmount() < mediClaim.getClaimAmount()) {
					claimRequest.get().setFirstLevelClaimStatus(claimStatus);
					claimRequest.get().setSecondLevelClaimStatus(MedicalClaimConstants.PENDING_STATUS_BY_ADMIN);
					claimStatusRepository.save(claimRequest.get());

				}

				else if (claimStatus.equalsIgnoreCase(MedicalClaimConstants.REJECTED_STATUS_BY_ADMIN)) {
					claimRequest.get().setFirstLevelClaimStatus(claimStatus);
					claimRequest.get().setSecondLevelClaimStatus(claimStatus);
					claimStatusRepository.save(claimRequest.get());

				}

			}

			else if (seniorApproverrequest.isPresent()
					&& admin.get().getAdminId().equals(seniorApproverrequest.get().getSeniorApproverId())
					&& seniorApproverrequest.get().getFirstLevelClaimStatus()
							.equalsIgnoreCase(MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN)
					&& userPolicy.isPresent() && userPolicy.get().getEligibilityAmount() < mediClaim.getClaimAmount()) {
				seniorApproverrequest.get().setSecondLevelClaimStatus(claimStatus);
				log.info("senior approver ={}", seniorApproverrequest.get().getSeniorApproverId());
				claimStatusRepository.save(seniorApproverrequest.get());

			}
		} else {
			throw new AdminNotFoundException(MedicalClaimConstants.FAILURE_MESSAGE);
		}

		approvalResponseDTO.setMessage(MedicalClaimConstants.SUCCESSFUL_MESSAGE);
		approvalResponseDTO.setStatusCode(MedicalClaimConstants.STATUS_CODE_FOR_OK);
		return approvalResponseDTO;
	}

}

package com.claim.medical.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.dto.ApprovalResponseDTO;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.repository.ClaimStatusRepository;
import com.claim.medical.util.MedicalClaimConstants;

@Service
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	ClaimStatusRepository  claimStatusRepository;
	public ApprovalResponseDTO firstLevelApproval(Integer claimId, Integer approverId, String firstLevelClaimStatus) {
		ApprovalResponseDTO  approvalResponseDTO=new ApprovalResponseDTO();
		
		Optional<ClaimStatus> claimRequest=claimStatusRepository.findByClaimIdAndApproverId(claimId,approverId);
		
		if(claimRequest.isPresent()) {
			claimRequest.get().setFirstLevelClaimStatus(firstLevelClaimStatus);
			claimStatusRepository.save(claimRequest.get());
			approvalResponseDTO.setMessage(MedicalClaimConstants.SUCCESSFUL_MESSAGE);
			approvalResponseDTO.setStatusCode(MedicalClaimConstants.STATUS_CODE_FOR_OK);
			
		}
		
		return approvalResponseDTO;
	}

}

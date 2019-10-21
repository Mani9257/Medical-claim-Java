package com.claim.medical.service;

import com.claim.medical.dto.ApprovalResponseDTO;

public interface ApprovalService {

	ApprovalResponseDTO firstLevelApproval(Integer claimId, Integer approverId, String firstLevelClaimStatus);

}

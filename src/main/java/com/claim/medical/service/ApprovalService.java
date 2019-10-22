package com.claim.medical.service;

import com.claim.medical.dto.ApprovalResponseDTO;
import com.claim.medical.exception.AdminNotFoundException;

public interface ApprovalService {

	ApprovalResponseDTO adminApproval(Integer claimId, Integer approverId, String claimStatus) throws AdminNotFoundException;



}

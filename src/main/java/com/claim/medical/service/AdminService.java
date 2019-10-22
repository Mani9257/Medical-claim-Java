package com.claim.medical.service;

import java.util.List;

import com.claim.medical.dto.MedicalClaimsResponseDto;
import com.claim.medical.exception.MedicalClaimException;

public interface AdminService {

	List<MedicalClaimsResponseDto> findClaimsBasedOnAdmin(Integer adminId) throws MedicalClaimException;

	Boolean findAdminRole(Integer adminId, Integer claimId, String approverStatus) throws MedicalClaimException;

}

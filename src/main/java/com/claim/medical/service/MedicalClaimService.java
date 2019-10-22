package com.claim.medical.service;

import java.util.List;
import java.util.Optional;

import com.claim.medical.dto.ClaimRequestDto;
import com.claim.medical.dto.MedicalClaimsResponseDto;
import com.claim.medical.entity.Admin;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.exception.MedicalClaimException;

public interface MedicalClaimService {

	public Optional<ClaimStatus> saveMedicalClaim(ClaimRequestDto claimRequestDto) throws MedicalClaimException;


	public List<MedicalClaimsResponseDto> getAllPendingClaims(Admin admin) throws MedicalClaimException;
}

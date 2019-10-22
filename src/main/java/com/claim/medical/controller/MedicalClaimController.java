package com.claim.medical.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.dto.ClaimRequestDto;
import com.claim.medical.dto.ClaimResponseDto;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.service.MedicalClaimService;
import com.claim.medical.util.MedicalClaimConstants;

/**
 * This class has one method which is used to save medical claim
 * 
 * @author Shreya E Nair
 * @since 2019-10-22
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class MedicalClaimController {
	@Autowired
	MedicalClaimService medicalClaimService;

	/**
	 * This method is used to save medical claim made by a user
	 * 
	 * @param ClaimRequestDto object
	 * @return ClaimResponseDto object
	 * @throws MedicalClaimException
	 */
	@PostMapping(path = "/claims")
	public ClaimResponseDto requestClaim(@RequestBody ClaimRequestDto claimRequestDto) throws MedicalClaimException {
		Optional<ClaimStatus> claimStatus = medicalClaimService.saveMedicalClaim(claimRequestDto);
		ClaimResponseDto claimResponseDto = new ClaimResponseDto();
		if (claimStatus.isPresent()) {
			claimResponseDto.setClaimId(claimStatus.get().getClaimId());
			claimResponseDto.setMessage(MedicalClaimConstants.MEDICAL_CLAIM_SAVED);
			claimResponseDto.setStatusCode(HttpStatus.OK.value());
		} else {
			claimResponseDto.setClaimId(null);
			claimResponseDto.setMessage(MedicalClaimConstants.MEDICAL_CLAIM_SAVED);
			claimResponseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}
		return claimResponseDto;
	}
}

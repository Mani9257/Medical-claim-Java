package com.claim.medical.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.dto.MedicalClaimResponseDto;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.entity.Policy;
import com.claim.medical.entity.UserPolicy;
import com.claim.medical.service.ClaimStatusService;
import com.claim.medical.service.MedicalClaimService;
import com.claim.medical.service.PolicyService;
import com.claim.medical.service.UserPolicyService;

import lombok.extern.slf4j.Slf4j;

/**
 * this controller will return all pending claims
 * 
 * @author Abhishek
 *
 */
@RestController
@RequestMapping("/mediclaim/api")
@CrossOrigin(allowedHeaders = { "*", "/" }, origins = { "*", "/" })
@Slf4j
public class MedicalClaimController {

	/**
	 * this attribute will fetch medical claims and will communicate with
	 * medicalClaimRepository
	 */
	@Autowired
	private MedicalClaimService medicalClaimService;

	/**
	 * this attribute will fetch medical claims and will communicate with
	 * claimStatusRepository
	 */
	@Autowired
	private ClaimStatusService claimStatusService;

	/**
	 * this attribute will fetch medical claims and will communicate with
	 * claimStatusRepository
	 */
	@Autowired
	private PolicyService policyService;

	/**
	 * this attribute will fetch medical claims and will communicate with
	 * userPolicyRepository
	 */
	@Autowired
	private UserPolicyService userPolicyService;

	/**
	 * This method is use to get all PendingMedicalClaims
	 * 
	 * @return List<MedicalClaimResponseDto>
	 */
	@GetMapping(value = "/medicalclaims")
	public ResponseEntity<List<MedicalClaimResponseDto>> getPendingMedicalClaims() {

		List<ClaimStatus> allClaims = claimStatusService.getAllClaims();

		log.info("allClaims={}", allClaims);

		List<ClaimStatus> claimListWithFirstLevelPending = allClaims.stream()
				.filter(c -> c.getFirstLevelClaimStatus().equalsIgnoreCase("pending")).collect(Collectors.toList());

		List<MedicalClaimResponseDto> medicalClaimResponseDtoList = new ArrayList<>();
		for (ClaimStatus claimStatus : claimListWithFirstLevelPending) {

			Integer claimId = claimStatus.getClaimId();

			log.info("claim id{}", claimId);

			List<MedicalClaim> pendingMedicalClaims = medicalClaimService.getAllPendingMedicalClaims(claimId);

			log.info("pendingMedicalClaims{}", pendingMedicalClaims);

			for (MedicalClaim m : pendingMedicalClaims) {

				Integer policyId = m.getPolicyId();

				Optional<Policy> policy = policyService.getPolicy(policyId);

				UserPolicy userPolicy = userPolicyService.getUserPolicy(policyId);
				if (policy.isPresent()) {

					String policyName = policy.get().getPolicyType();

					MedicalClaimResponseDto medicalClaimResponseDto = new MedicalClaimResponseDto();

					medicalClaimResponseDto.setAdmissionDate(m.getAdmissionDate());
					medicalClaimResponseDto.setClaimAmount(m.getClaimAmount());
					medicalClaimResponseDto.setClaimId(m.getClaimId());
					medicalClaimResponseDto.setClaimStatus(m.getClaimStatus());
					medicalClaimResponseDto.setDischargeDate(m.getDischargeDate());
					medicalClaimResponseDto.setMedicalClaimRequestDate(m.getClaimRaisedDate());
					medicalClaimResponseDto.setClaimType(policyName);
					medicalClaimResponseDto.setEligibilityAmount(userPolicy.getEligibilityAmount());
					medicalClaimResponseDtoList.add(medicalClaimResponseDto);
				}
			}

		}

		return new ResponseEntity<>(medicalClaimResponseDtoList, HttpStatus.OK);
	}

}

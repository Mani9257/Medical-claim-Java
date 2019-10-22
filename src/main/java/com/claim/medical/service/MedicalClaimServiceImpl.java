package com.claim.medical.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.dto.ClaimRequestDto;
import com.claim.medical.dto.MedicalClaimsResponseDto;
import com.claim.medical.entity.Admin;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.entity.User;
import com.claim.medical.entity.UserPolicy;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.MedicalClaimRepository;
import com.claim.medical.util.MedicalClaimConstants;

/**
 * This class will has 2 methods which does the following: a)Save medical claims
 * b)fetch medical claims based on the admin role
 * 
 * @author Shreya E Nair
 * @since 2019-10-22
 */
@Service
public class MedicalClaimServiceImpl implements MedicalClaimService {

	@Autowired
	MedicalClaimRepository medicalClaimRepository;
	@Autowired
	ClaimStatusService claimStatusService;
	@Autowired
	PolicyService policyService;
	@Autowired
	UserPolicyService userPolicyService;
	@Autowired
	UserService userService;
	@Autowired
	MedicalClaimService medicalClaimService;

	/**
	 * This method will save the claim status of a medical claim raised.
	 * 
	 * @param ClaimRequestDto object
	 * @return ClaimStatus object
	 * @throws MedicalClaimException
	 */
	@Override
	public Optional<ClaimStatus> saveMedicalClaim(ClaimRequestDto claimRequestDto) throws MedicalClaimException {
		MedicalClaim medicalClaim = new MedicalClaim();
		BeanUtils.copyProperties(claimRequestDto, medicalClaim);
		medicalClaim.setClaimRaisedDate(LocalDate.now());
		try {
			MedicalClaim savedClaimRequest = medicalClaimRepository.save(medicalClaim);
			Optional<ClaimStatus> savedClaimStatus = claimStatusService.saveClaimStatus(savedClaimRequest);
			return savedClaimStatus;
		} catch (Exception e) {
			throw new MedicalClaimException(MedicalClaimConstants.FAILED_TO_SAVE_MEDICAL_CLAIM);
		}
	}

	/**
	 * This method will fetch all the medical claims based on the admin role.
	 * 
	 * @param Admin object
	 * @return MedicalClaimsResponseDto list
	 * @throws MedicalClaimException
	 */
	@Override
	public List<MedicalClaimsResponseDto> getAllPendingClaims(Admin adminDetails) throws MedicalClaimException {
		List<MedicalClaim> medicalClaimList = medicalClaimRepository.findAll();
		List<UserPolicy> userPolicyList = userPolicyService.getAll();

		List<MedicalClaim> filteredMedicalClaimList = null;
		if (adminDetails.getRole().equalsIgnoreCase(MedicalClaimConstants.FIRST_LEVEL_APPROVER)) {
			List<ClaimStatus> claimStatusList = claimStatusService.getAllByFirstLevelClaimStatus();
			filteredMedicalClaimList = medicalClaimList.stream()
					.filter(m -> claimStatusList.stream().anyMatch(d -> m.getClaimId().equals(d.getClaimId())))
					.collect(Collectors.toList());
		} else {
			List<ClaimStatus> claimStatusList = claimStatusService.getAllBySecondLevelClaimStatus();
			List<MedicalClaim> mediListFilteredByAmount = medicalClaimList.stream()
					.filter(m -> userPolicyList.stream().anyMatch(u -> m.getClaimAmount() > (u.getEligibilityAmount())))
					.collect(Collectors.toList());
			filteredMedicalClaimList = mediListFilteredByAmount.stream()
					.filter(m -> claimStatusList.stream().anyMatch(d -> m.getClaimId().equals(d.getClaimId())))
					.collect(Collectors.toList());
		}

		List<MedicalClaimsResponseDto> mediClaimResponseList = new ArrayList<MedicalClaimsResponseDto>();
		for (MedicalClaim medClaim : filteredMedicalClaimList) {
			MedicalClaimsResponseDto mediClaimResponse = new MedicalClaimsResponseDto();
			String policyName = policyService.getPolicyNameById(medClaim.getPolicyId());
			Double eligibleAmount = userPolicyService.getEligibileAmount(medClaim.getUserId());
			User user = userService.getUserName(medClaim.getUserId());
			BeanUtils.copyProperties(medClaim, mediClaimResponse);
			mediClaimResponse.setPolicyName(policyName);
			mediClaimResponse.setEligibleAmount(eligibleAmount);
			mediClaimResponse.setUserName(user.getUserName());
			mediClaimResponse.setUserId(user.getUserId());
			mediClaimResponseList.add(mediClaimResponse);
		}

		return mediClaimResponseList;
	}

}

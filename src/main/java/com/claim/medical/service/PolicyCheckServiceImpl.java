package com.claim.medical.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.dto.ClaimDetailsResponseDto;
import com.claim.medical.dto.PolicyCheckResponseDto;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.entity.Policy;
import com.claim.medical.entity.User;
import com.claim.medical.entity.UserPolicy;
import com.claim.medical.exception.PolicyExpiredException;
import com.claim.medical.exception.PolicyNotFoundException;
import com.claim.medical.repository.ClaimStatusRepository;
import com.claim.medical.repository.MedicalClaimRepository;
import com.claim.medical.repository.PolicyRepository;
import com.claim.medical.repository.UserPolicyRepository;
import com.claim.medical.repository.UserRepository;
import com.claim.medical.util.MedicalClaimConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * service methods to check policy and view claim status.
 * @author Sucheta
 *
 */
/**
 * @author User1
 *
 */
@Service
@Slf4j
public class PolicyCheckServiceImpl implements PolicyCheckService {

	/**
	 * 
	 */
	@Autowired
	UserPolicyRepository userPolicyRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PolicyRepository policyRepository;

	@Autowired
	MedicalClaimRepository medicalClaimRepository;
	
	@Autowired
	ClaimStatusRepository claimStatusRepository;
	
    
	/**
	 * @param policyId
	 * @return policyCheckResponseDto which will contain the status if the policy is valid or not
	 * @throws PolicyExpiredException
	 * @throws PolicyNotFoundException
	 */
	@Override
	public PolicyCheckResponseDto checkPolicy(Integer policyId) throws PolicyExpiredException, PolicyNotFoundException {
		
		UserPolicy userPolicy = userPolicyRepository.findByPolicyId(policyId);

		if (userPolicy != null) {

			LocalDate today = LocalDate.now();
			if (userPolicy.getEndDate().isAfter(today)) {
				log.info("record fetched...");
				User user = userRepository.findByUserId(userPolicy.getUserId());
				PolicyCheckResponseDto policyCheckResponseDto = new PolicyCheckResponseDto();
				policyCheckResponseDto.setUserId(userPolicy.getUserId());
				policyCheckResponseDto.setUserName(user.getUserName());
				policyCheckResponseDto.setStatusMessage(MedicalClaimConstants.SUCCESS_STATUS_MESSAGE_POLICY_FETCH);
				policyCheckResponseDto.setStatusCode(MedicalClaimConstants.SUCCESS_STATUS_CODE);
				policyCheckResponseDto.setStatus("success");

				return policyCheckResponseDto;
			} else {
				throw new PolicyExpiredException(MedicalClaimConstants.EXPIRED_STATUS_MESSAGE_POLICY_FETCH);
			}
		}

		else {
			throw new PolicyNotFoundException(MedicalClaimConstants.INVALIED_STATUS_MESSAGE_POLICY_FETCH);
		}

	}

	/**
	 * @param policyId
	 * @return claimDetailsResponseDto will contain the status and approval flow of a claim request
	 * @throws PolicyExpiredException
	 * @throws PolicyNotFoundException
	 */
	@Override
	public ClaimDetailsResponseDto viewClaimStatus(Integer policyId) throws PolicyNotFoundException {
		
		
		MedicalClaim medicalClaim=medicalClaimRepository.findByPolicyId(policyId);
		Policy policy=policyRepository.findByPolicyId(policyId);
		
		if(medicalClaim != null) {
			ClaimStatus claimStatus=claimStatusRepository.findByClaimId(medicalClaim.getClaimId());
			log.info("claim found...");
			ClaimDetailsResponseDto claimDetailsResponseDto= new ClaimDetailsResponseDto();
			claimDetailsResponseDto.setClaimId(medicalClaim.getClaimId());
			claimDetailsResponseDto.setClaimRaisedDate(medicalClaim.getClaimRaisedDate());
			claimDetailsResponseDto.setClaimAmount(medicalClaim.getClaimAmount());
			claimDetailsResponseDto.setClaimType(policy.getPolicyType());
			claimDetailsResponseDto.setApproverId(claimStatus.getApproverId());
			claimDetailsResponseDto.setClaimFirstLevelStatus(claimStatus.getFirstLevelClaimStatus());
			claimDetailsResponseDto.setClaimSecondLevelStatus(claimStatus.getSecondLevelClaimStatus());
			claimDetailsResponseDto.setSeniorAppoverId(claimStatus.getSeniorApproverId());
			claimDetailsResponseDto.setStatusMessage(MedicalClaimConstants.SUCCESS_STATUS_MESSAGE_POLICY_FETCH);
			claimDetailsResponseDto.setStatusCode(MedicalClaimConstants.SUCCESS_STATUS_CODE);
			claimDetailsResponseDto.setStatus("success");

		
		return claimDetailsResponseDto;
		}
		else {
			throw new PolicyNotFoundException(MedicalClaimConstants.INVALIED_STATUS_MESSAGE_POLICY_FETCH);
		}
	}

	

}

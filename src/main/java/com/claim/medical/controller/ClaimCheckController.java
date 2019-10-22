package com.claim.medical.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.dto.ClaimDetailsResponseDto;
import com.claim.medical.dto.PolicyCheckResponseDto;
import com.claim.medical.exception.PolicyExpiredException;
import com.claim.medical.exception.PolicyNotFoundException;
import com.claim.medical.service.PolicyCheckService;

import lombok.extern.slf4j.Slf4j;



/**
 * This controller can be used to check checkPolicy,viewClaimStatus
 * @author Sucheta
 *
 */
@Slf4j
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
public class ClaimCheckController {
	
	/**
	 * 
	 */
	@Autowired
	PolicyCheckService policyCheckService;

	

	/**
	 * @param policyId
	 * @return policyCheckResponseDto which will contain the status if the policy is valid or not
	 * @throws PolicyExpiredException
	 * @throws PolicyNotFoundException
	 */
	@GetMapping("/policies/{policyId}")
	public ResponseEntity<PolicyCheckResponseDto> checkPolicy(@PathVariable("policyId") Integer policyId) throws PolicyExpiredException, PolicyNotFoundException {
		log.info("inside check policy controller");
	    PolicyCheckResponseDto policyCheckResponseDto = policyCheckService.checkPolicy(policyId);
		return new ResponseEntity<>(policyCheckResponseDto, HttpStatus.OK);
	}


	/**
	 * @param policyId
	 * @return claimDetailsResponseDto will contain the status and approval flow of a claim request
	 * @throws PolicyNotFoundException
	 */
	@GetMapping("/claims/policies/{policyId}") public ResponseEntity<ClaimDetailsResponseDto> viewClaimStatus(@PathVariable("policyId") Integer policyId) throws PolicyNotFoundException{
		log.info("inside view Claim status");
		ClaimDetailsResponseDto claimDetailsResponseDto=policyCheckService.viewClaimStatus(policyId);
		return new ResponseEntity<>(claimDetailsResponseDto,HttpStatus.OK);
	}
	
}

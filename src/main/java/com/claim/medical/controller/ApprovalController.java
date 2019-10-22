package com.claim.medical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.dto.ApprovalResponseDTO;
import com.claim.medical.exception.AdminNotFoundException;
import com.claim.medical.service.ApprovalService;

import lombok.extern.slf4j.Slf4j;

/**
 * This Controller is used to approve or reject the claim request raised by patients
 * 
 * @author Ajith
 *
 */
/**
 * @param claimId
 * @return
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
@Slf4j
public class ApprovalController {

	@Autowired
	private ApprovalService approvalService;

/**
 *This method is use for first level approval of  claim request raised by the patient
 * @pathVariable Integer
 * @pathVariable Integer
 * @pathVariable String
 * @return ApprovalResponseDTO
 * @throws AdminNotFoundException 
 */
	@GetMapping("/claims/{claimId}/{approverId}/{firstLevelClaimStatus}")
	public ResponseEntity<ApprovalResponseDTO> adminApproval(@PathVariable Integer claimId,@PathVariable Integer approverId,@PathVariable String firstLevelClaimStatus) throws AdminNotFoundException {
		log.info("Inside firstLevelApproval");

		ApprovalResponseDTO approvalResponse = approvalService.adminApproval(claimId,approverId,firstLevelClaimStatus);

		return new ResponseEntity<>(approvalResponse, HttpStatus.OK);


}
}

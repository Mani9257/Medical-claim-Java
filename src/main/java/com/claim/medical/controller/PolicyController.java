package com.claim.medical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.dto.PolicyResponseDTO;
import com.claim.medical.service.PolicyService;

import lombok.extern.slf4j.Slf4j;

/**
 * This Controller is used to get all the policies available
 * 
 * @author Ajith
 *
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api")
@Slf4j
public class PolicyController {

	@Autowired
	private PolicyService policyService;

	/**
	 * This method is use to get all the policies
	 * 
	 * @return List<PolicyResponseDTO>
	 */

	@GetMapping("/policies")
	public ResponseEntity<List<PolicyResponseDTO>> getAllPolicies() {
		log.info("Inside Hospitals");

		List<PolicyResponseDTO> policies = policyService.getAllPolicies();

		return new ResponseEntity<>(policies, HttpStatus.OK);

	}
}

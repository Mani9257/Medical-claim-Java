package com.claim.medical.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.dto.PolicyDTO;
import com.claim.medical.dto.PolicyResponseDTO;
import com.claim.medical.service.PolicyService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PolicyControllerTest {

	@InjectMocks
	private PolicyController policyController;

	@Mock
	private PolicyService policyService;

	PolicyResponseDTO policyResponseDTO = new PolicyResponseDTO();
	List<PolicyResponseDTO> policyResponseDTOs = new ArrayList<>();
	PolicyDTO policyDTO = new PolicyDTO();

	List<PolicyDTO> policyDTOs = new ArrayList<>();

	@Before
	public void setUp() {
		policyDTO.setPolicyId(1);
		policyDTO.setPolicyType("DENTAL");
		policyDTOs.add(policyDTO);
		policyResponseDTO.setMessage("Sucess");
		policyResponseDTO.setPolicyDtos(policyDTOs);
		policyResponseDTO.setStatusCode(200);
		policyResponseDTOs.add(policyResponseDTO);

	}

	@Test
	public void testGetALlPolicies() {

		Mockito.when(policyService.getAllPolicies()).thenReturn(policyResponseDTOs);

		ResponseEntity<List<PolicyResponseDTO>> response = policyController.getAllPolicies();

		assertEquals(200, response.getStatusCodeValue());

	}

}

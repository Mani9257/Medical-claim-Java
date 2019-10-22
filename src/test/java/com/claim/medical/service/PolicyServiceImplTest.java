package com.claim.medical.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.dto.PolicyDTO;
import com.claim.medical.dto.PolicyResponseDTO;
import com.claim.medical.entity.Policy;
import com.claim.medical.repository.PolicyRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PolicyServiceImplTest {

	@InjectMocks
	private PolicyServiceImpl policyServiceImpl;

	@Mock
	private PolicyRepository policyRepository;

	PolicyResponseDTO policyResponseDTO = new PolicyResponseDTO();
	List<PolicyResponseDTO> policyResponseDTOs = new ArrayList<>();
	PolicyDTO policyDTO = new PolicyDTO();

	List<PolicyDTO> policyDTOs = new ArrayList<>();
	List<Policy> policies = new ArrayList<>();
	Policy policy = new Policy();

	@Before
	public void setUp() {
		policyDTO.setPolicyId(1);
		policyDTO.setPolicyType("DENTAL");
		policyDTOs.add(policyDTO);
		policyResponseDTO.setMessage("Sucess");
		policyResponseDTO.setPolicyDtos(policyDTOs);
		policyResponseDTO.setStatusCode(200);
		policyResponseDTOs.add(policyResponseDTO);

		policy.setPolicyId(1);
		policy.setPolicyType("VISION");
		policies.add(policy);

	}

	@Test
	public void testHospitals() {

		Mockito.when(policyRepository.findAll()).thenReturn(policies);

		List<PolicyResponseDTO> response = policyServiceImpl.getAllPolicies();
		assertEquals(1, response.size());

	}

}

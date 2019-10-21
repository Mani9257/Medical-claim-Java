package com.claim.medical.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.dto.PolicyDTO;
import com.claim.medical.dto.PolicyResponseDTO;
import com.claim.medical.entity.Policy;
import com.claim.medical.repository.PolicyRepository;
import com.claim.medical.util.MedicalClaimConstants;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;

	public List<PolicyResponseDTO> getAllPolicies() {
		List<PolicyResponseDTO> responsePolicies = new ArrayList<>();
		PolicyResponseDTO policyResponseDTO = new PolicyResponseDTO();
		List<PolicyDTO> policyResponse = new ArrayList<>();
		PolicyDTO eachPolicy = new PolicyDTO();

		List<Policy> policies = policyRepository.findAll();
		if (!policies.isEmpty()) {
			policies.stream().forEach(policy -> {

				eachPolicy.setPolicyId(policy.getPolicyId());
				eachPolicy.setPolicyType(policy.getPolicyType());
				policyResponse.add(eachPolicy);
				policyResponseDTO.setPolicyDtos(policyResponse);

			});
			policyResponseDTO.setMessage(MedicalClaimConstants.SUCCESSFUL_MESSAGE);
			policyResponseDTO.setStatusCode(MedicalClaimConstants.STATUS_CODE_FOR_OK);
			responsePolicies.add(policyResponseDTO);
		}
		return responsePolicies;

	}

}

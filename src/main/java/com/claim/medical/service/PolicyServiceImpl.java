package com.claim.medical.service;


import java.util.ArrayList;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.dto.PolicyDTO;
import com.claim.medical.dto.PolicyResponseDTO;
import com.claim.medical.entity.Policy;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.PolicyRepository;
import com.claim.medical.util.MedicalClaimConstants;

/**This Service class is use to show all the policies and it's details
 * 
 * @author Thangavel Ayyanar Ajith
 *
 */

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	PolicyRepository policyRepository;
	
	/**This service is use to get all the available policies
	 * @return List<HospitalResponseDTO>
	 */

	public List<PolicyResponseDTO> getAllPolicies() {
		List<PolicyResponseDTO> responsePolicies = new ArrayList<>();
		PolicyResponseDTO policyResponseDTO = new PolicyResponseDTO();
		List<PolicyDTO> policyResponse = new ArrayList<>();
		

		List<Policy> policies = policyRepository.findAll();
		if (!policies.isEmpty()) {
			policies.stream().forEach(policy -> {
			
				PolicyDTO eachPolicy = new PolicyDTO();
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

	/**
	 * This method is used to fetch policyd details based on policyId passed
	 * 
	 * @param policyId
	 * @return Policy object
	 * @throws MedicalClaimException
	 */

	public String getPolicyNameById(Integer policyId) throws MedicalClaimException {
		Optional<Policy> policy = policyRepository.findById(policyId);
		if (policy.isPresent()) {
			return policy.get().getPolicyType();
		} else {
			throw new MedicalClaimException(MedicalClaimConstants.POLICY_NOT_FOUND);
		}
	}

	/**
	 * this getPolicy will query to DB based on policyId
	 * 
	 * @return Optional<Policy>
	 */

	public Optional<Policy> getPolicy(Integer policyId) {
		return policyRepository.findById(policyId);
	}
}

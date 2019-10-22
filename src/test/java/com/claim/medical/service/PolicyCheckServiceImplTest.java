package com.claim.medical.service;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.dto.ClaimDetailsResponseDto;
import com.claim.medical.dto.PolicyCheckResponseDto;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.entity.Policy;
import com.claim.medical.entity.User;
import com.claim.medical.entity.UserPolicy;
import com.claim.medical.exception.PolicyExpiredException;
import com.claim.medical.exception.PolicyNotFoundException;
import com.claim.medical.repository.MedicalClaimRepository;
import com.claim.medical.repository.PolicyRepository;
import com.claim.medical.repository.UserPolicyRepository;
import com.claim.medical.repository.UserRepository;


/**
 * @author Sucheta
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class PolicyCheckServiceImplTest {

	@Mock
	UserPolicyRepository userPolicyRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	PolicyRepository policyRepository;
	@Mock
	MedicalClaimRepository medicalClaimRepository;
	@InjectMocks
	PolicyCheckServiceImpl policyCheckServiceImpl;
	UserPolicy userPolicy = null;
	PolicyCheckResponseDto policyCheckResponseDto = null;
	ClaimDetailsResponseDto claimDetailsResponseDto =null;
	User user = null;
	MedicalClaim medicalClaim=null;
	Policy policy=null;
	
	
	@Before
	public void setUp() {
		userPolicy = new UserPolicy();
		userPolicy.setPolicyId(1);
		userPolicy.setUserId(2);
		userPolicy.setUserPolicyId(1);
		userPolicy.setEligibilityAmount(100.00);
		userPolicy.setEndDate(LocalDate.now().plusDays(2));

		policyCheckResponseDto = new PolicyCheckResponseDto();
		policyCheckResponseDto.setUserId(2);
		policyCheckResponseDto.setUserName("sucheta");
		policyCheckResponseDto.setStatusMessage("Successfully fetch policy details");
		
		claimDetailsResponseDto =new ClaimDetailsResponseDto();
		claimDetailsResponseDto.setStatusMessage("Successfully fetch policy details");
		claimDetailsResponseDto.setClaimId(1);
		claimDetailsResponseDto.setStatus("success");
		policy= new Policy();
		policy.setPolicyId(1);
		

		 user = new User();
		user.setUserName("sucheta");
	}

	@Test
	public void testcheckPolicy_Positive() throws PolicyExpiredException, PolicyNotFoundException {

	

		Mockito.when(userPolicyRepository.findByPolicyId(Mockito.anyInt())).thenReturn(userPolicy);
		
		Mockito.when(userRepository.findByUserId(Mockito.anyInt())).thenReturn(user);

	
		PolicyCheckResponseDto policyCheckResponseDto = policyCheckServiceImpl.checkPolicy(1);
		Assert.assertEquals("Successfully fetch policy details", policyCheckResponseDto.getStatusMessage());

	}
	
	
}

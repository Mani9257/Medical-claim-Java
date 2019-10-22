package com.claim.medical.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.entity.Policy;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.PolicyRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PolicyServiceImplTest   {

	@Mock
	PolicyRepository policyRepository;
	@InjectMocks
	PolicyServiceImpl policyServiceImpl; 
	Policy policy = null;
	Optional<Policy> policyDetails = null;
	@Before
	public void setUp() {
		policy = new Policy();
		policy.setPolicyId(1);
		policy.setPolicyType("General");
		policyDetails = Optional.of(policy);
	}
	
	@Test
	public void testGetPolicyNameById_Positive( ) throws MedicalClaimException {
		Mockito.when(policyRepository.findById(Mockito.anyInt())).thenReturn(policyDetails);
		String policyName = policyServiceImpl.getPolicyNameById(1);
		assertEquals(policyName,policy.getPolicyType());
	}
	
	@Test(expected=MedicalClaimException.class)
	public void testGetPolicyNameById_negative( ) throws MedicalClaimException {
		 policyServiceImpl.getPolicyNameById(2);
	}

}

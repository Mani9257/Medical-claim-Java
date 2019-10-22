package com.claim.medical.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.entity.UserPolicy;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.UserPolicyRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserPolicyServiceImplTest {

	@Mock
	UserPolicyRepository userPolicyRepository;
	@InjectMocks
	UserPolicyServiceImpl userPolicyServiceImpl;
	Optional<UserPolicy> userPolicyDetails = null;
	UserPolicy firstPolicy = null;
	UserPolicy secondPolicy = null;
	List<UserPolicy> userPolicyList = null;
	
	@Before()
	public void setUp() {
		firstPolicy = new UserPolicy();
		firstPolicy.setEligibilityAmount(200000.0);
		userPolicyDetails = Optional.of(firstPolicy);
		
		secondPolicy = new UserPolicy();
		secondPolicy.setEligibilityAmount(200000.0);
		
		userPolicyList = new ArrayList<UserPolicy>();
		userPolicyList.add(firstPolicy);
		userPolicyList.add(secondPolicy);
		
	}

	@Test
	public void testGetEligibileAmount_Positive() throws MedicalClaimException {
		Mockito.when(userPolicyRepository.findByUserId(Mockito.anyInt())).thenReturn(userPolicyDetails);
		Double amount = userPolicyServiceImpl.getEligibileAmount(1);
		assertEquals(firstPolicy.getEligibilityAmount(),amount);

	}
	
	@Test(expected=MedicalClaimException.class)
	public void testGetEligibileAmount_Negative() throws MedicalClaimException {
		 userPolicyServiceImpl.getEligibileAmount(1);

	}

	@Test
	public void testGetAll() {
		Mockito.when(userPolicyRepository.findAll()).thenReturn(userPolicyList);
		List<UserPolicy> userPolicy = userPolicyServiceImpl.getAll();
		assertEquals(2, userPolicy.size());
	}

}

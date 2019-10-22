package com.claim.medical.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.entity.UserPolicy;
import com.claim.medical.repository.UserPolicyRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserPolicyServiceImplTest {

	@Mock
	UserPolicyRepository userPolicyRepository;

	@InjectMocks
	UserPolicyServiceImpl userPolicyServiceImpl;

	UserPolicy userPolicy = null;

	@Before
	public void setup() {

		userPolicy = new UserPolicy();

		userPolicy.setEligibilityAmount(10000d);
		userPolicy.setEndDate(LocalDate.of(2019, Month.AUGUST, 22));
		userPolicy.setPolicyId(100);
		userPolicy.setStartDate(LocalDate.of(2019, Month.JULY, 9));
		userPolicy.setUserId(10);
		userPolicy.setUserPolicyId(300);
	}

	@Test
	public void testGetUserPolicy() {

		Mockito.when(userPolicyRepository.findByPolicyId(100)).thenReturn(userPolicy);

		UserPolicy expectedUserPolicy = userPolicyServiceImpl.getUserPolicy(100);
		assertEquals(userPolicy, expectedUserPolicy);
	}

	@Test
	public void testGetUserPolicyIfNoPolicyFound() {

		Mockito.when(userPolicyRepository.findByPolicyId(100)).thenReturn(userPolicy);

		UserPolicy expectedUserPolicy = userPolicyServiceImpl.getUserPolicy(10);
		assertNotEquals(userPolicy, expectedUserPolicy);
	}

}

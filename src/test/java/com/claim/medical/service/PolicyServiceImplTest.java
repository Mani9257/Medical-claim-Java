package com.claim.medical.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.entity.Policy;
import com.claim.medical.repository.PolicyRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PolicyServiceImplTest {

	@Mock
	PolicyRepository policyRepository;

	@InjectMocks
	PolicyServiceImpl policyServiceImpl;

	Policy policy = new Policy();
	Optional<Policy> optionalPolicy = Optional.of(policy);

	@Before
	public void setup() {

		if (optionalPolicy.isPresent()) {
			optionalPolicy.get().setPolicyId(10);
			optionalPolicy.get().setPolicyType("vision");
		}

	}

	@Test
	public void testGetPolicy() {

		Mockito.when(policyRepository.findById(10)).thenReturn(optionalPolicy);

		Optional<Policy> expectedPolicy = policyServiceImpl.getPolicy(10);

		assertEquals(optionalPolicy, expectedPolicy);
	}

	@Test
	public void testGetPolicyIfPolicyNotFound() {

		Mockito.when(policyRepository.findById(1)).thenReturn(optionalPolicy);

		Optional<Policy> expectedPolicy = policyServiceImpl.getPolicy(10);

		assertNotEquals(optionalPolicy, expectedPolicy);
	}

}

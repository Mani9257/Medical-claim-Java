package com.claim.medical.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.repository.ClaimStatusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClaimStatusServiceImplTest {

	@Mock
	ClaimStatusRepository claimStatusRepository;

	@InjectMocks
	ClaimStatusServiceImpl claimStatusServiceImpl;

	List<ClaimStatus> claimList = null;
	ClaimStatus claimStatus = null;
	ClaimStatus claimStatus2 = null;

	@Before
	public void before() {

		claimStatus = new ClaimStatus();
		claimStatus2 = new ClaimStatus();
		claimList = new ArrayList<>();

		claimStatus.setAprroverId(10);
		claimStatus.setClaimId(303);
		claimStatus.setClaimStatusId(10);
		claimStatus.setFirstLevelClaimStatus("pending");
		claimStatus.setSecondLevelClaimStatus("pending");
		claimStatus.setSeniorApproverId(293);

		claimStatus2.setAprroverId(11);
		claimStatus2.setClaimId(304);
		claimStatus2.setClaimStatusId(22);
		claimStatus2.setFirstLevelClaimStatus("pending");
		claimStatus2.setSecondLevelClaimStatus("approved");
		claimStatus2.setSeniorApproverId(123);

		claimList.add(claimStatus);
		claimList.add(claimStatus2);

	}

	@Test
	public void testGetAllClaims() {
		Mockito.when(claimStatusRepository.findAll()).thenReturn(claimList);

		List<ClaimStatus> expectedClaimList = claimStatusServiceImpl.getAllClaims();

		assertEquals(claimList, expectedClaimList);
	}
	
	@Test
	public void testGetAllClaimsIfNoClaimsAvailable() {
		Mockito.when(claimStatusRepository.findAll()).thenReturn(claimList);

		List<ClaimStatus> expectedClaimList = claimStatusServiceImpl.getAllClaims();
		assertNotEquals(claimList, expectedClaimList.size());
	}
	
	
	
	
	

}

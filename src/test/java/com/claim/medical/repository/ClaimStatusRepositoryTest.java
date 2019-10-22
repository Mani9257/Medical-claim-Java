/*package com.claim.medical.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.entity.ClaimStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ClaimStatusRepositoryTest {

	@Autowired
	private ClaimStatusRepository claimStatusRepository;
	
	@Autowired
    private TestEntityManager testEntityManager;
	
	ClaimStatus claimStatus  = null;
	ClaimStatus claimStatus2  = null;
	List<ClaimStatus> claimList = null;
	@Before
	public void setup() {
		claimList = new ArrayList<>();
		
		 claimStatus  = new ClaimStatus();
		claimStatus.setAprroverId(101);
		claimStatus.setClaimId(234);
		claimStatus.setClaimStatusId(857);
		claimStatus.setFirstLevelClaimStatus("pending");
		claimStatus.setSecondLevelClaimStatus("approved");
		claimStatus.setSeniorApproverId(231);
		
		ClaimStatus claimStatus2 = new ClaimStatus(); 
		
		claimStatus2.setAprroverId(120);
		claimStatus2.setClaimId(203);
		claimStatus2.setClaimStatusId(499);
		claimStatus2.setFirstLevelClaimStatus("pending");
		claimStatus2.setSecondLevelClaimStatus("approved");
		claimStatus2.setSeniorApproverId(231);
		claimList.add(claimStatus);
		claimList.add(claimStatus2);
		
		testEntityManager.find(ClaimStatus.class,1);
	}
	

	@Test
	public void testFindAll() {

		List<ClaimStatus> continents = claimStatusRepository.findAll();
		
		assertEquals(continents, claimList);
	}

}
*/
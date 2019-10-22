package com.claim.medical.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.Month;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.claim.medical.dto.MedicalClaimResponseDto;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.entity.Policy;
import com.claim.medical.entity.UserPolicy;
import com.claim.medical.service.ClaimStatusServiceImpl;
import com.claim.medical.service.MedicalClaimServiceImpl;
import com.claim.medical.service.PolicyServiceImpl;
import com.claim.medical.service.UserPolicyServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class MedicalClaimControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	MedicalClaimController medicalClaimController;

	@Mock
	MedicalClaimServiceImpl medicalClaimServiceImpl;
	
	@Mock
	ClaimStatusServiceImpl claimStatusServiceImpl;
	
	@Mock
	PolicyServiceImpl policyServiceImpl;
	
	@Mock
	UserPolicyServiceImpl userPolicyServiceImpl;

	List<ClaimStatus> claimStatusList = null;
	ClaimStatus claimStatus = null;
	ClaimStatus claimStatus2 = null;
	Integer claimId = 0;
	MedicalClaimResponseDto medicalClaimResponseDto = null;
	MedicalClaim medicalClaim = null;
	List<MedicalClaim> pendingMedicalClaims = null;
	Integer policyId = 0;
	UserPolicy userPolicy = null;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(medicalClaimController).build();
	
		medicalClaim = new MedicalClaim();
		
		claimStatus = new ClaimStatus();
		claimStatus2 = new ClaimStatus();
		medicalClaimResponseDto = new MedicalClaimResponseDto();
		claimStatusList = new ArrayList<>();
		pendingMedicalClaims = new ArrayList<>();
		userPolicy = new UserPolicy();
		
		claimStatus.setAprroverId(11);
		claimStatus.setClaimId(44);
		claimStatus.setClaimStatusId(2039);
		claimStatus.setFirstLevelClaimStatus("PENDING");
		claimStatus.setSecondLevelClaimStatus("APPROVAL");
		claimStatus.setSeniorApproverId(293);
		
		
		
		
		claimStatus2.setAprroverId(22);
		claimStatus2.setClaimId(98);
		claimStatus2.setClaimStatusId(1123);
		claimStatus2.setFirstLevelClaimStatus("PENDING");
		claimStatus2.setSecondLevelClaimStatus("APPROVAL");
		claimStatus2.setSeniorApproverId(233);
		
		
		claimStatusList.add(claimStatus);
		claimStatusList.add(claimStatus2);
		
		
		medicalClaim.setAdmissionDate(LocalDate.of(2019, Month.FEBRUARY, 11));
		medicalClaim.setClaimAmount(10000d);
		medicalClaim.setClaimId(98);
		medicalClaim.setClaimRaisedDate(LocalDate.of(2019, Month.MARCH, 23));
		medicalClaim.setClaimStatus("panding");
		medicalClaim.setDischargeDate(LocalDate.of(2019, Month.APRIL, 3));
		medicalClaim.setDoctor("ram");
		medicalClaim.setHospitalId(109);
		medicalClaim.setPolicyId(203);
		medicalClaim.setUserId(1);
		
		
		pendingMedicalClaims.add(medicalClaim);
		userPolicy.setEligibilityAmount(2000D);
		userPolicy.setEndDate(LocalDate.of(2019, Month.FEBRUARY, 11));
		userPolicy.setPolicyId(203);
		userPolicy.setStartDate(LocalDate.of(2019, Month.JANUARY, 11));
		userPolicy.setUserId(1);
		userPolicy.setUserPolicyId(22);

		
		
		
		
		for(MedicalClaim clamis:pendingMedicalClaims ) {
			policyId = clamis.getPolicyId();
		
			
		}
		
		
		
		
		for(ClaimStatus claimStatus:claimStatusList) {
			if(claimStatus.getFirstLevelClaimStatus().equals("pending")) {
				claimId = claimStatus.getClaimId();
			}
			
			
		}
		
		
		
		
	}
	
	
	@Test
	public void testGetPendingMedicalClaims() {
		
		
		Mockito.when(claimStatusServiceImpl.getAllClaims()).thenReturn(claimStatusList);
		
		
		Mockito.when(medicalClaimServiceImpl.getAllPendingMedicalClaims(claimId)).thenReturn(pendingMedicalClaims);
		
		Mockito.when(policyServiceImpl.getPolicy(policyId)).thenReturn(Optional.of(new Policy()));
		
		Mockito.when(userPolicyServiceImpl.getUserPolicy(policyId)).thenReturn(userPolicy);
		
		
		
		
	}
	
	@Test
	public void testGetPendingMedicalClaimsIfNotPendingClaims() {
		
		
		Mockito.when(claimStatusServiceImpl.getAllClaims()).thenReturn(claimStatusList);
		
		
		Mockito.when(medicalClaimServiceImpl.getAllPendingMedicalClaims(claimId)).thenReturn(pendingMedicalClaims);
		
		Mockito.when(policyServiceImpl.getPolicy(policyId)).thenReturn(Optional.of(new Policy()));
		
		Mockito.when(userPolicyServiceImpl.getUserPolicy(policyId)).thenReturn(userPolicy);
		
		assertNotEquals(claimStatus2, claimStatus);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

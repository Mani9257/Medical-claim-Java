package com.claim.medical.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.claim.medical.dto.ClaimRequestDto;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.service.MedicalClaimService;
import com.claim.medical.util.MedicalClaimConstants;

@RunWith(SpringJUnit4ClassRunner.class)
public class MedicalClaimControllerTest {
	@Mock
	MedicalClaimService medicalClaimService;
	@InjectMocks
	MedicalClaimController medicalClaimController;
	
	MedicalClaim medicalClaim = null;
	ClaimStatus claimStatus = null;
	Optional<ClaimStatus> claimStatusRequest = null;
	ClaimRequestDto claimRequestDto = null;

	@Before
	public void setUp() {
		medicalClaim = new MedicalClaim();
		medicalClaim.setAdmissionDate(LocalDate.of(2019, 10, 01));
		medicalClaim.setClaimAmount(20000.0);
		medicalClaim.setClaimId(1);
		medicalClaim.setClaimRaisedDate(LocalDate.of(2019, 10, 21));
		medicalClaim.setDischargeDate(LocalDate.of(2019, 10, 17));
		medicalClaim.setDoctor("Dr.Shubashri");
		medicalClaim.setHospitalId(1);
		medicalClaim.setPolicyId(1);
		medicalClaim.setUserId(1);

		claimStatus = new ClaimStatus();
		claimStatus.setApproverId(null);
		claimStatus.setClaim_status_id(1);
		claimStatus.setClaimId(1);
		claimStatus.setFirstLevelClaimStatus(MedicalClaimConstants.PENDING);
		claimStatus.setSecondLevelClaimStatus(MedicalClaimConstants.PENDING);
		claimStatus.setSeniorApproverId(null);
		claimStatusRequest = Optional.of(claimStatus);
		
		claimRequestDto = new ClaimRequestDto();
		claimRequestDto.setAdmissionDate(LocalDate.of(2019, 10, 01));
		claimRequestDto.setClaimAmount(20000.0);
		claimRequestDto.setDischargeDate(LocalDate.of(2019, 10, 17));
		claimRequestDto.setHospitalId(1);
		claimRequestDto.setPolicyId(1);
		claimRequestDto.setUserId(1);
		
	}


	@Test
	public void testRequestClaim_Positive() throws MedicalClaimException {
		Mockito.when( medicalClaimService.saveMedicalClaim(Mockito.any())).thenReturn(claimStatusRequest);
		
	}
}

package com.claim.medical.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.dto.ClaimRequestDto;
import com.claim.medical.dto.MedicalClaimsResponseDto;
import com.claim.medical.entity.Admin;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.entity.UserPolicy;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.MedicalClaimRepository;
import com.claim.medical.util.MedicalClaimConstants;

@RunWith(SpringJUnit4ClassRunner.class)
public class MedicalClaimServiceImplTest {
	@Mock
	MedicalClaimRepository medicalClaimRepository;
	@Mock
	PolicyService policyService;
	@Mock
	UserPolicyService userPolicyService;
	@Mock
	UserService userService;
	@Mock
	MedicalClaimService medicalClaimService;
	@Mock
	MedicalClaimRepository claimRepository;
	@Mock
	ClaimStatusService claimStatusService;
	@InjectMocks
	MedicalClaimServiceImpl medicalClaimServiceImpl;
	
	MedicalClaim firstMedicalClaim = null;
	MedicalClaim secondMedicalClaim = null;
	List<MedicalClaim> mediCalimList = null;
	ClaimStatus claimStatus = null;
	Optional<ClaimStatus> claimStatusRequest = null;
	ClaimRequestDto claimRequestDto = null;
	List<MedicalClaim> mediClaimList = null;
	UserPolicy firstPolicy = null;
	UserPolicy secondPolicy = null;
	List<UserPolicy> userPolicyList = null;
	MedicalClaimsResponseDto firstMedicalClaimsResponseDto = null;
	MedicalClaimsResponseDto secondMedicalClaimsResponseDto = null;
	List<MedicalClaimsResponseDto> medicalClaimsResponseDtoList = null;
	Admin admin = null;

	@Before
	public void setUp() {
		firstMedicalClaim = new MedicalClaim();
		firstMedicalClaim.setAdmissionDate(LocalDate.of(2019, 10, 01));
		firstMedicalClaim.setClaimAmount(20000.0);
		firstMedicalClaim.setClaimId(1);
		firstMedicalClaim.setClaimRaisedDate(LocalDate.of(2019, 10, 21));
		firstMedicalClaim.setDischargeDate(LocalDate.of(2019, 10, 17));
		firstMedicalClaim.setDoctor("Dr.Shubashri");
		firstMedicalClaim.setHospitalId(1);
		firstMedicalClaim.setPolicyId(1);
		firstMedicalClaim.setUserId(1);

		secondMedicalClaim = new MedicalClaim();
		secondMedicalClaim.setAdmissionDate(LocalDate.of(2019, 10,01));
		secondMedicalClaim.setClaimAmount(20000.0);
		secondMedicalClaim.setClaimId(1);
		secondMedicalClaim.setClaimRaisedDate(LocalDate.of(2019, 10, 21));
		secondMedicalClaim.setDischargeDate(LocalDate.of(2019, 10, 17));
		secondMedicalClaim.setDoctor("Dr.Shubashri");
		secondMedicalClaim.setHospitalId(1);
		secondMedicalClaim.setPolicyId(1);
		secondMedicalClaim.setUserId(1);

		mediClaimList = new ArrayList<MedicalClaim>();
		mediClaimList.add(firstMedicalClaim);
		mediClaimList.add(secondMedicalClaim);

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
		
		firstMedicalClaimsResponseDto = new MedicalClaimsResponseDto();
		firstMedicalClaimsResponseDto.setAdmissionDate(LocalDate.of(2019, 10, 01));
		firstMedicalClaimsResponseDto.setClaimAmount(20000.0);
		firstMedicalClaimsResponseDto.setClaimId(1);
		firstMedicalClaimsResponseDto.setClaimRaisedDate(LocalDate.of(2019, 10, 21));
		firstMedicalClaimsResponseDto.setDischargeDate(LocalDate.of(2019, 10, 17));
		firstMedicalClaimsResponseDto.setDoctor("Dr.Shubashri");
		firstMedicalClaimsResponseDto.setPolicyName("General");
		firstMedicalClaimsResponseDto.setUserId(1);
		firstMedicalClaimsResponseDto.setEligibleAmount(50000.0);
		firstMedicalClaimsResponseDto.setUserName("Sucheta");
		
		secondMedicalClaimsResponseDto = new MedicalClaimsResponseDto();
		secondMedicalClaimsResponseDto.setAdmissionDate(LocalDate.of(2019, 10, 01));
		secondMedicalClaimsResponseDto.setClaimAmount(20000.0);
		secondMedicalClaimsResponseDto.setClaimId(2);
		secondMedicalClaimsResponseDto.setClaimRaisedDate(LocalDate.of(2019, 10, 21));
		secondMedicalClaimsResponseDto.setDischargeDate(LocalDate.of(2019, 10, 17));
		secondMedicalClaimsResponseDto.setDoctor("Dr.Shubashri");
		secondMedicalClaimsResponseDto.setPolicyName("General");
		secondMedicalClaimsResponseDto.setUserId(2);
		secondMedicalClaimsResponseDto.setEligibleAmount(50000.0);
		secondMedicalClaimsResponseDto.setUserName("Shreya");
		
		medicalClaimsResponseDtoList = new ArrayList<MedicalClaimsResponseDto>();
		medicalClaimsResponseDtoList.add(firstMedicalClaimsResponseDto);
		medicalClaimsResponseDtoList.add(secondMedicalClaimsResponseDto);
		
		admin = new Admin();
		admin.setAdminId(1);
		admin.setAdminName("Shreya");
		admin.setPassword("shreya123");
		admin.setRole(MedicalClaimConstants.FIRST_LEVEL_APPROVER);
	

	}

	@Test
	public void testSaveMedicalClaim_Positive() throws MedicalClaimException {
		Mockito.when(claimRepository.save(Mockito.any())).thenReturn(firstMedicalClaim);
		Mockito.when(claimStatusService.saveClaimStatus(Mockito.any())).thenReturn(claimStatusRequest);
		Optional<ClaimStatus> claimStatusData = medicalClaimServiceImpl.saveMedicalClaim(claimRequestDto);
		assertNotNull(claimStatusData.get());
		assertEquals(claimStatusData.get().getClaimId(), claimStatus.getClaimId());
	}

	@Test(expected = MedicalClaimException.class)
	public void testSaveMedicalClaim_Negative() throws MedicalClaimException {
		medicalClaimServiceImpl.saveMedicalClaim(claimRequestDto);
	}

}

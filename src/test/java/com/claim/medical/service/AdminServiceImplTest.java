package com.claim.medical.service;

import static org.junit.Assert.assertEquals;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.dto.MedicalClaimsResponseDto;
import com.claim.medical.entity.Admin;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.AdminRepository;
import com.claim.medical.util.MedicalClaimConstants;

@RunWith(SpringJUnit4ClassRunner.class)
public class AdminServiceImplTest  {

	@Mock
	AdminRepository adminRepository;
	@Mock
	ClaimStatusService claimStatusService;
	@Mock
	MedicalClaimService medicalClaimService;
	@InjectMocks
	AdminServiceImpl adminServiceImpl;
	Admin admin = null;
	Optional<Admin> adminDetails = null;
	MedicalClaimsResponseDto firstMedicalClaimsResponseDto = null;
	MedicalClaimsResponseDto secondMedicalClaimsResponseDto = null;
	List<MedicalClaimsResponseDto> medicalClaimsResponseDtoList = null;
	
	@Before()
	public void setUp() {
		admin = new Admin();
		admin.setAdminId(1);
		admin.setAdminName("Shreya");
		admin.setPassword("shreya123");
		admin.setRole(MedicalClaimConstants.FIRST_LEVEL_APPROVER);
		adminDetails = Optional.of(admin);
		
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
	}
	@Test
	public void testFindClaimsBasedOnAdmin_Positive() throws MedicalClaimException {
		Mockito.when(adminRepository.findById(Mockito.anyInt())).thenReturn(adminDetails); 
		Mockito.when(medicalClaimService
					.getAllPendingClaims(Mockito.any())).thenReturn(medicalClaimsResponseDtoList);
		List<MedicalClaimsResponseDto> mediClaimsResponseDto = adminServiceImpl.findClaimsBasedOnAdmin(1);
		assertEquals(mediClaimsResponseDto.size(),2);

	}
	
	@Test(expected=MedicalClaimException.class)
	public void testFindClaimsBasedOnAdmin_Negative() throws MedicalClaimException {
		adminServiceImpl.findClaimsBasedOnAdmin(10);
		

	}

	
}

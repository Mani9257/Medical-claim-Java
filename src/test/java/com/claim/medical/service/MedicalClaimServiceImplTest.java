package com.claim.medical.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.repository.MedicalClaimRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class MedicalClaimServiceImplTest {

	@Mock
	MedicalClaimRepository medicalClaimRepository;

	@InjectMocks
	MedicalClaimServiceImpl medicalClaimServiceImpl;

	MedicalClaim medicalClaim = null;
	List<MedicalClaim> medicalClaimList = null;

	@Before
	public void setup() {

		medicalClaim = new MedicalClaim();
		medicalClaimList = new ArrayList<>();
		medicalClaim.setAdmissionDate(LocalDate.of(2019, Month.AUGUST, 15));
		medicalClaim.setClaimAmount(20000d);
		medicalClaim.setClaimId(12);
		medicalClaim.setClaimRaisedDate(LocalDate.of(2019, Month.DECEMBER, 15));
		medicalClaim.setClaimStatus("panding");
		medicalClaim.setDischargeDate(LocalDate.of(2019, Month.SEPTEMBER, 15));
		medicalClaim.setDoctor("ram");
		medicalClaim.setHospitalId(204);
		medicalClaim.setPolicyId(2033);
		medicalClaim.setUserId(99);
		medicalClaimList.add(medicalClaim);
	}

	@Test
	public void testGetAllPendingMedicalClaims() {

		Mockito.when(medicalClaimRepository.findMadicalClaimByClaimId(12)).thenReturn(medicalClaimList);

		List<MedicalClaim> expectedAllPendingMedicalClaims = medicalClaimServiceImpl.getAllPendingMedicalClaims(12);
		assertEquals(medicalClaimList, expectedAllPendingMedicalClaims);
	}

	@Test
	public void testGetAllPendingMedicalClaimsIfNoClaims() {

		Mockito.when(medicalClaimRepository.findMadicalClaimByClaimId(1)).thenReturn(medicalClaimList);

		List<MedicalClaim> expectedAllPendingMedicalClaims = medicalClaimServiceImpl.getAllPendingMedicalClaims(19);
		assertNotEquals(medicalClaimList, expectedAllPendingMedicalClaims);
	}
	
	@Test
	public void testGetAllPendingMedicalClaimsIfNotMatchingClaimId() {

		Mockito.when(medicalClaimRepository.findMadicalClaimByClaimId(1)).thenReturn(medicalClaimList);

		List<MedicalClaim> expectedAllPendingMedicalClaims = medicalClaimServiceImpl.getAllPendingMedicalClaims(190);
		assertNotEquals(medicalClaimList, expectedAllPendingMedicalClaims);
	}
	
	

}

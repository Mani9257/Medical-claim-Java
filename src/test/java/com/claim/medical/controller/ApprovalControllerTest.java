package com.claim.medical.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.dto.ApprovalResponseDTO;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.exception.AdminNotFoundException;
import com.claim.medical.service.ApprovalService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApprovalControllerTest {

	@InjectMocks
	private ApprovalController approvalController;

	@Mock
	private ApprovalService approvalService;

	ApprovalResponseDTO approvalResponseDTO = new ApprovalResponseDTO();
	ClaimStatus claimStatus = new ClaimStatus();

	@Before
	public void setUp() {

		claimStatus.setApproverId(1);
		claimStatus.setClaim_status_id(1);
		claimStatus.setClaimId(1);
		claimStatus.setFirstLevelClaimStatus("APPROVED");
		claimStatus.setSeniorApproverId(2);
		claimStatus.setSecondLevelClaimStatus("APPROVED");

		approvalResponseDTO.setMessage("SUCCESS");
		approvalResponseDTO.setStatusCode(200);

	}

	@Test
	public void testAdminApproval() throws AdminNotFoundException {

		Mockito.when(approvalService.adminApproval(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString()))
				.thenReturn(approvalResponseDTO);

		ResponseEntity<ApprovalResponseDTO> response = approvalController.adminApproval(Mockito.anyInt(),
				Mockito.anyInt(), Mockito.anyString());

		assertEquals(200, response.getStatusCodeValue());

	}

}

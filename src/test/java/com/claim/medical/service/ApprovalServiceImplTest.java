package com.claim.medical.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.dto.ApprovalResponseDTO;
import com.claim.medical.entity.Admin;
import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.entity.UserPolicy;
import com.claim.medical.exception.AdminNotFoundException;
import com.claim.medical.repository.AdminRepository;
import com.claim.medical.repository.ClaimStatusRepository;
import com.claim.medical.repository.MedicalCalimRepository;
import com.claim.medical.repository.UserPolicyRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApprovalServiceImplTest {

	@InjectMocks
	private ApprovalServiceImpl approvalServiceImpl;

	@Mock
	private AdminRepository adminRepository;

	@Mock
	ClaimStatusRepository claimStatusRepository;

	@Mock
	MedicalCalimRepository medicalCalimRepository;

	@Mock
	UserPolicyRepository userPolicyRepository;

	MedicalClaim medicalClaim = new MedicalClaim();

	UserPolicy userPolicy = new UserPolicy();
	Admin admin = new Admin();

	ApprovalResponseDTO approvalResponseDTO = new ApprovalResponseDTO();
	ClaimStatus claimStatus = new ClaimStatus();

	@Before
	public void setUp() {
		medicalClaim.setPolicyId(1);
		medicalClaim.setClaimId(1);
		medicalClaim.setUserId(1);
	
		userPolicy.setPolicyId(1);
		userPolicy.setUserId(1);

		claimStatus.setClaim_status_id(1);
		claimStatus.setClaimId(1);

		approvalResponseDTO.setMessage("SUCCESS");
		approvalResponseDTO.setStatusCode(200);

	}

	@Test
	public void testApproval() throws AdminNotFoundException {
		admin.setAdminId(1);
		admin.setAdminName("Ajith");

		claimStatus.setApproverId(1);
		claimStatus.setFirstLevelClaimStatus("APPROVED");

		Mockito.when(adminRepository.findByAdminId(Mockito.anyInt())).thenReturn(Optional.of(admin));

		Mockito.when(userPolicyRepository.findByPolicyId(Mockito.anyInt())).thenReturn(Optional.of(userPolicy));
		Mockito.when(medicalCalimRepository.findByClaimId(Mockito.anyInt())).thenReturn(medicalClaim);
		

		Mockito.when(claimStatusRepository.findByClaimIdAndApproverId(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(Optional.of(claimStatus));
		Mockito.when(claimStatusRepository.findByClaimIdAndSeniorApproverId(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(Optional.empty());
		Optional<ClaimStatus> response = claimStatusRepository.findByClaimIdAndApproverId(Mockito.anyInt(),
				Mockito.anyInt());
		assertEquals(claimStatus.getApproverId(), response.get().getApproverId());
		
		ApprovalResponseDTO response2=approvalServiceImpl.adminApproval(1, 1, "APPROVED");
		assertEquals(approvalResponseDTO.getStatusCode(),response2.getStatusCode());
	}

	@Test
	public void testApprovals() throws AdminNotFoundException {
		admin.setAdminId(1);
		admin.setAdminName("Ajith");

		claimStatus.setSeniorApproverId(2);
		claimStatus.setSecondLevelClaimStatus("APPROVED");

		Mockito.when(adminRepository.findByAdminId(Mockito.anyInt())).thenReturn(Optional.of(admin));
		Mockito.when(userPolicyRepository.findByPolicyId(Mockito.anyInt())).thenReturn(Optional.of(userPolicy));
		Mockito.when(medicalCalimRepository.findByClaimId(Mockito.anyInt())).thenReturn(medicalClaim);

		Mockito.when(claimStatusRepository.findByClaimIdAndApproverId(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn((Optional.empty()));
		Mockito.when(claimStatusRepository.findByClaimIdAndSeniorApproverId(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(Optional.of(claimStatus));
		Optional<ClaimStatus> response = claimStatusRepository.findByClaimIdAndSeniorApproverId(Mockito.anyInt(),
				Mockito.anyInt());
		assertEquals(claimStatus.getApproverId(), response.get().getApproverId());
		ApprovalResponseDTO response2=approvalServiceImpl.adminApproval(1, 2, "APPROVED");
		assertEquals(approvalResponseDTO.getStatusCode(),response2.getStatusCode());
	}

	@Test
	public void testApprovalInvalid() throws AdminNotFoundException {
	

		Mockito.when(adminRepository.findByAdminId(Mockito.anyInt())).thenReturn(Optional.empty());
		Mockito.when(userPolicyRepository.findByPolicyId(Mockito.anyInt())).thenReturn(Optional.of(userPolicy));
		Mockito.when(medicalCalimRepository.findByClaimId(Mockito.anyInt())).thenReturn(medicalClaim);

		Mockito.when(claimStatusRepository.findByClaimIdAndApproverId(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn((Optional.empty()));
		Mockito.when(claimStatusRepository.findByClaimIdAndSeniorApproverId(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(Optional.of(claimStatus));
		MedicalClaim response = medicalCalimRepository.findByClaimId(Mockito.anyInt());
		assertEquals(medicalClaim.getClaimId(), response.getClaimId());
		
		

		
	}

}

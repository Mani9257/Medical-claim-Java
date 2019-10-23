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
import com.claim.medical.util.MedicalClaimConstants;

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

		claimStatus.setClaimStatusId(1);
		claimStatus.setClaimId(1);

		approvalResponseDTO.setMessage("SUCCESS");
		approvalResponseDTO.setStatusCode(200);

	}

	@Test
	public void testApprovalFirstLevelPositive() throws AdminNotFoundException {
		admin.setAdminId(1);
		admin.setAdminName("Ajith");
		claimStatus.setApproverId(1);
		claimStatus.setFirstLevelClaimStatus(MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN);

		userPolicy.setEligibilityAmount(10000.0);
		medicalClaim.setClaimAmount(8000.0);

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
		ApprovalResponseDTO response2 = approvalServiceImpl.adminApproval(1, 1,
				MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN);
		assertEquals(approvalResponseDTO.getStatusCode(), response2.getStatusCode());
	}

	@Test
	public void testApprovalFirstLevel() throws AdminNotFoundException {
		admin.setAdminId(1);
		admin.setAdminName("Ajith");

		claimStatus.setApproverId(1);
		claimStatus.setFirstLevelClaimStatus(MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN);

		userPolicy.setEligibilityAmount(10000.0);
		medicalClaim.setClaimAmount(15000.0);

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

		ApprovalResponseDTO response2 = approvalServiceImpl.adminApproval(1, 1, "APPROVED");
		assertEquals(approvalResponseDTO.getStatusCode(), response2.getStatusCode());
	}

	@Test
	public void testApprovalFirstLevelNegative() throws AdminNotFoundException {
		admin.setAdminId(1);
		admin.setAdminName("Ajith");

		claimStatus.setApproverId(1);

		claimStatus.setFirstLevelClaimStatus(MedicalClaimConstants.REJECTED_STATUS_BY_ADMIN);
		claimStatus.setSecondLevelClaimStatus(MedicalClaimConstants.PENDING_STATUS_BY_ADMIN);

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

		ApprovalResponseDTO response2 = approvalServiceImpl.adminApproval(1, 1, "REJECTED");
		assertEquals(approvalResponseDTO.getStatusCode(), response2.getStatusCode());
	}

	@Test
	public void testApprovalSecondLevel() throws AdminNotFoundException {
		admin.setAdminId(2);
		admin.setAdminName("Ajith");
		claimStatus.setFirstLevelClaimStatus(MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN);
		claimStatus.setSecondLevelClaimStatus(MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN);
		claimStatus.setSeniorApproverId(2);
		userPolicy.setEligibilityAmount(10000.0);
		medicalClaim.setClaimAmount(150000.0);

		Mockito.when(adminRepository.findByAdminId(Mockito.anyInt())).thenReturn(Optional.of(admin));
		Mockito.when(userPolicyRepository.findByPolicyId(Mockito.anyInt())).thenReturn(Optional.of(userPolicy));
		Mockito.when(medicalCalimRepository.findByClaimId(Mockito.anyInt())).thenReturn(medicalClaim);

		Mockito.when(claimStatusRepository.findByClaimIdAndApproverId(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(Optional.empty());
		Mockito.when(claimStatusRepository.findByClaimIdAndSeniorApproverId(Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(Optional.of(claimStatus));
		Optional<ClaimStatus> response = claimStatusRepository.findByClaimIdAndSeniorApproverId(Mockito.anyInt(),
				Mockito.anyInt());
		assertEquals(claimStatus.getApproverId(), response.get().getApproverId());
		ApprovalResponseDTO response2 = approvalServiceImpl.adminApproval(1, 2,
				MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN);
		assertEquals(approvalResponseDTO.getStatusCode(), response2.getStatusCode());
	}

	@Test(expected = AdminNotFoundException.class)
	public void testApprovalInvalid() throws AdminNotFoundException {
		claimStatus.setFirstLevelClaimStatus(MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN);

		Mockito.when(adminRepository.findByAdminId(0)).thenReturn(Optional.empty());
		Mockito.when(medicalCalimRepository.findByClaimId(Mockito.anyInt())).thenReturn(medicalClaim);
		Mockito.when(claimStatusRepository.findByClaimIdAndApproverId(0, 0)).thenReturn((Optional.empty()));
		ApprovalResponseDTO responseDTO = approvalServiceImpl.adminApproval(0, 0,
				MedicalClaimConstants.APPROVED_STATUS_BY_ADMIN);
		assertEquals("SUCCESS", responseDTO.getMessage());

	}

}

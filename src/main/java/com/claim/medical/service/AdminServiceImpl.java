package com.claim.medical.service;

/**
* This class will return list of medical claims by user based on the admin(approver, senior approver)
* @author  Shreya E Nair
* @since   2019-10-22 
*/
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.dto.MedicalClaimsResponseDto;
import com.claim.medical.entity.Admin;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.AdminRepository;
import com.claim.medical.util.MedicalClaimConstants;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	ClaimStatusService claimStatusService;
	@Autowired
	MedicalClaimService medicalClaimService;
	private static final Logger lOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	/**
	 * This method will return the list of medical claims made by a user based on
	 * who the admin is. If the admin is approver then he/she will receive all the
	 * claims that are pending at the first level. If the admin is senior approver
	 * then he/she will receive all the claims that have been approved at the first
	 * level and pending for second level
	 * 
	 * @param adminId
	 * @return list of MedicalClaimsResponseDto
	 * @throws MedicalClaimException
	 */
	@Override
	public List<MedicalClaimsResponseDto> findClaimsBasedOnAdmin(Integer adminId) throws MedicalClaimException {
		Optional<Admin> adminDetails = adminRepository.findById(adminId);
		if (adminDetails.isPresent()) {
			List<MedicalClaimsResponseDto> medicalClaimList = medicalClaimService
					.getAllPendingClaims(adminDetails.get());
			lOGGER.info("Medical Claim list returned depending on the approver");
			return medicalClaimList;
		} else {
			throw new MedicalClaimException(MedicalClaimConstants.ADMIN_CREDENTIALS_NOT_VALID);
		}

	}

	@Override
	public Boolean findAdminRole(Integer adminId, Integer claimId, String approverStatus) throws MedicalClaimException {
		Optional<Admin> adminDetails = adminRepository.findById(adminId);
		if (adminDetails.isPresent()) {
			{
				Boolean value = claimStatusService.updateClaimStatusBasedOnApprover(adminDetails.get(), claimId,
						approverStatus);
				lOGGER.info("Medical Claim updated depending on the approver");
				return value;
			}
		} else {
			throw new MedicalClaimException(MedicalClaimConstants.ADMIN_CREDENTIALS_NOT_VALID);
		}

	}

}

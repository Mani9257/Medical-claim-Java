package com.claim.medical.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medical.dto.MedicalClaimsListResponseDto;
import com.claim.medical.dto.MedicalClaimsResponseDto;
import com.claim.medical.dto.ResponseDto;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.service.AdminService;
import com.claim.medical.service.ClaimStatusService;
import com.claim.medical.util.MedicalClaimConstants;
/**
 * This class has one method which is used to return the medical claims based on admin Role
 * 
 * @author Shreya E Nair
 * @since 2019-10-22
 */
@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/api") 
public class AdminController {

	@Autowired
	ClaimStatusService claimStatus;
	@Autowired
	AdminService adminService;
	/**
	 * This method will return the list of medical claims made by a user based on
	 * who the admin is. If the admin is approver then he/she will receive all the
	 * claims that are pending at the first level. If the admin is senior approver
	 * then he/she will receive all the claims that have been approved at the first
	 * level and pending for second level
	 * 
	 * @param adminId
	 * @return MedicalClaimsListResponseDto
	 * @throws MedicalClaimException
	 */
	@GetMapping("/admin/{adminId}/claims")
	public MedicalClaimsListResponseDto claimList(@PathVariable ("adminId") Integer adminId) throws MedicalClaimException {
		List<MedicalClaimsResponseDto> medicalClaimList = adminService.findClaimsBasedOnAdmin(adminId);
		MedicalClaimsListResponseDto medicalClaimsListResponseDto = new MedicalClaimsListResponseDto();
		medicalClaimsListResponseDto.setMedicalClaimsResponseDto(medicalClaimList);
		medicalClaimsListResponseDto.setMessage(MedicalClaimConstants.LIST_OF_MEDICAL_CLAIMS_FOUND);
		medicalClaimsListResponseDto.setStatusCode(HttpStatus.OK.value());
		return medicalClaimsListResponseDto;
		
	}
	
	@PutMapping("/claims/{claimId}/admin/{adminId}/{approverStatus}")
	public ResponseDto claimApplicationCheck(@PathVariable("claimId") Integer claimId, @PathVariable("adminId") Integer adminId, @PathVariable("approverStatus") String approverStatus) throws MedicalClaimException {
		Boolean value = adminService.findAdminRole(adminId,claimId,approverStatus);
		ResponseDto responseDto = new ResponseDto();
		if(value.equals(MedicalClaimConstants.BOOLEAN_TRUE)) {
			responseDto.setMessage(MedicalClaimConstants.UPDATED_CLAIM_STATUS_SUCCESSFULLY);
			responseDto.setStatusCode(HttpStatus.OK.value());
		}else {
			responseDto.setMessage(MedicalClaimConstants.CLAIM_STATUS_UPDATION_FAILED);
			responseDto.setStatusCode(HttpStatus.BAD_REQUEST.value());
		}
		return responseDto;
	}
}

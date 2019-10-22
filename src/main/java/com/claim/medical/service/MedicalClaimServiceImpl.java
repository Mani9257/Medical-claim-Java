package com.claim.medical.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.entity.MedicalClaim;
import com.claim.medical.repository.MedicalClaimRepository;

/**
 * this service will return all pending medical claims
 * 
 * @author Abhishek
 *
 */
@Service
public class MedicalClaimServiceImpl implements MedicalClaimService {

	/**
	 * this MedicalClaimRepository will query to DB
	 */
	@Autowired
	private MedicalClaimRepository medicalClaimRepository;

	/***
	 * this method will query to DB and
	 * 
	 * @return List<MedicalClaim>
	 */
	@Override
	public List<MedicalClaim> getAllPendingMedicalClaims(Integer claimId) {
		return medicalClaimRepository.findMadicalClaimByClaimId(claimId);
	}

}

package com.claim.medical.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.entity.ClaimStatus;
import com.claim.medical.repository.ClaimStatusRepository;

/**
 * this service will return ClaimStatus details and communicate with
 * ClaimStatusRepository
 * 
 * @author Abhishek
 *
 */
@Service
public class ClaimStatusServiceImpl implements ClaimStatusService {

	/**
	 * this is claimStatusRepository will query to DB
	 */
	@Autowired
	private ClaimStatusRepository claimStatusRepository;

	/**
	 * this method will return all claims
	 */
	@Override
	public List<ClaimStatus> getAllClaims() {
		return claimStatusRepository.findAll();
	}

}

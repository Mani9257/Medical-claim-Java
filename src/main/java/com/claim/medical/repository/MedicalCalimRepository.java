package com.claim.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.claim.medical.entity.MedicalClaim;

public interface MedicalCalimRepository extends JpaRepository<MedicalClaim, Integer> {

	MedicalClaim findByClaimId(Integer claimId);

}

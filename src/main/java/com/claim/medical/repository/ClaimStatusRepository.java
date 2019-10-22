package com.claim.medical.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.ClaimStatus;

@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatus, Integer>{

	List<ClaimStatus> findAllByFirstLevelClaimStatus(String pending);

	List<ClaimStatus> findAllByFirstLevelClaimStatusAndSecondLevelClaimStatus(String approved, String pending);

	ClaimStatus findByClaimId(Integer claimId);
}



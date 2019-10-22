package com.claim.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	Policy findByPolicyId(Integer policyId);

}

package com.claim.medical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.medical.entity.UserPolicy;

public interface UserPolicyRepository extends JpaRepository<UserPolicy, Integer> {

	Optional<UserPolicy> findByPolicyId(Integer policyId);

}

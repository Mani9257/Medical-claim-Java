package com.claim.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.medical.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer>{

}

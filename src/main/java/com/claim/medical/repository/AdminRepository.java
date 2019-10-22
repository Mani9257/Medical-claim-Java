package com.claim.medical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claim.medical.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByAdminId(Integer approverId);

}

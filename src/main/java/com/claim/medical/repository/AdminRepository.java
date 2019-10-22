package com.claim.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.Admin;
/**
 * this repository will get admin details
 * 
 * @author Abhishek
 *
 */

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Admin findByAdminNameAndPassword(String adminName, String password);

}

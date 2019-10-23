package com.claim.medical.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.Admin;
/**This Repository is use to perform admin db operation
 * 
 * @author Ajith
 *
 */

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByAdminId(Integer approverId);

	Admin findByAdminNameAndPassword(String adminName, String password);

}

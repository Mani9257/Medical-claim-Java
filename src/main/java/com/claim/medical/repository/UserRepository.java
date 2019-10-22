package com.claim.medical.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medical.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserId(Integer userId);

}

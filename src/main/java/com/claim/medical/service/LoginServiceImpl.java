package com.claim.medical.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.entity.Admin;
import com.claim.medical.repository.AdminRepository;

/**
 * this service will get admin details based on details
 * 
 * @author Abhishek
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	/**
	 * this is adminRepository to query to DB
	 */
	@Autowired
	private AdminRepository adminRepository;

	/**
	 * this login method will communicate with adminRepository to query to DB
	 */
	@Override
	public Admin login(String adminName, String password) {
		return adminRepository.findByAdminNameAndPassword(adminName, password);
	}

}

package com.claim.medical.service;

import com.claim.medical.entity.Admin;

/**
 * this is login service will query for login related data and will return admin
 * info
 * 
 * @author Abhishek
 *
 */
public interface LoginService {

	public Admin login(String adminName, String password);

}

package com.claim.medical.service;

import com.claim.medical.entity.User;
import com.claim.medical.exception.MedicalClaimException;

public interface UserService {

	User getUserName(Integer userId) throws MedicalClaimException;

}

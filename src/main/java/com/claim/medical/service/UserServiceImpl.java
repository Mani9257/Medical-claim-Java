package com.claim.medical.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medical.entity.User;
import com.claim.medical.exception.MedicalClaimException;
import com.claim.medical.repository.UserRepository;
import com.claim.medical.util.MedicalClaimConstants;

/**
 * This class has one method which is used to return the User details based on user id passed
 * 
 * @author Shreya E Nair
 * @since 2019-10-22
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	/**
	 * This method is used to return the User details based on user id passed
	 * 
	 * @param userId 
	 * @return User object
	 * @throws MedicalClaimException
	 */
	@Override
	public User getUserName(Integer userId) throws MedicalClaimException {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new MedicalClaimException(MedicalClaimConstants.USER_NOT_FOUND);
		}
	}

}

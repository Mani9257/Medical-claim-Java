package com.claim.medical.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medical.entity.Admin;
import com.claim.medical.repository.AdminRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceImplTest {

	@Mock
	AdminRepository adminRepository;

	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	Admin admin = null;

	@Before
	public void setup() {

		admin = new Admin();
		admin.setAdminId(10);
		admin.setAdminName("paul");
		admin.setPassword("123456");
		admin.setRole("manager");

	}

	@Test
	public void testLogin() {

		Mockito.when(adminRepository.findByAdminNameAndPassword("paul", "123456")).thenReturn(admin);

		Admin expectedAdmin = loginServiceImpl.login("paul", "123456");
		assertEquals(admin, expectedAdmin);
	}

	@Test
	public void testLoginIfNotSuccessful() {

		Mockito.when(adminRepository.findByAdminNameAndPassword("paul", "12345")).thenReturn(admin);

		Admin expectedAdmin = loginServiceImpl.login("paul", "123456");
		assertNotEquals(admin, expectedAdmin);
	}
	
	@Test
	public void testLoginIfPasswordIsInvalid() {

		Mockito.when(adminRepository.findByAdminNameAndPassword("paul", "1234")).thenReturn(admin);

		Admin expectedAdmin = loginServiceImpl.login("paul", "123456");
		assertNotEquals(admin, expectedAdmin);
	}
	
	
	

}

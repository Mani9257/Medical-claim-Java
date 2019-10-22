package com.claim.medical.controller;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.claim.medical.dto.LoginRequestDto;
import com.claim.medical.dto.LoginResponseDto;
import com.claim.medical.entity.Admin;
import com.claim.medical.service.LoginServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class LoginControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	LoginController loginController;

	@Mock
	LoginServiceImpl loginServiceImpl;

	LoginRequestDto loginRequestDto = null;
	LoginResponseDto loginResponseDto = null;
	Admin admin = null;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();

		loginRequestDto = new LoginRequestDto();
		loginRequestDto.setAdminName("paul");
		loginRequestDto.setPassword("123456");

		loginResponseDto = new LoginResponseDto();

		loginResponseDto.setAdminName("paul");
		loginResponseDto.setMessage("loggin successful");
		loginResponseDto.setStatusCode(200);

		admin = new Admin();
		admin.setAdminName("paul");
		admin.setRole("manager");

	}

	@Test
	public void testDoLogin() {

		Mockito.when(loginServiceImpl.login("paul", "123456")).thenReturn(admin);

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("mediclaim/api/login"));
		} catch (Exception e) {

			log.error("log error{}", e);
		}

	}

	@Test(expected = NullPointerException.class)
	public void testDoLoginIfNotSuccess() {

		Admin login = loginServiceImpl.login("paul", "12356");
		login.setPassword("123");
		login.setRole("srmanager");

		login = null;
		Mockito.when(login).thenReturn(admin);

		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/mediclaim/api/login"));
		} catch (Exception e) {
			log.info("logged exception{}", e);
		}
		assertNull(login);

	}
	
	
	@Test
	public void testDoLoginIfNotSuccessIfPasswordInvalid() {

		Admin adm = loginServiceImpl.login("paul", "156");
		
		Mockito.when(adm).thenReturn(admin);

		
		
		
			assertNotEquals(adm, admin);

	}
	
	
	
	

}

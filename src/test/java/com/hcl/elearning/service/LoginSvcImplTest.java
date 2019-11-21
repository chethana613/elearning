
package com.hcl.elearning.service;

import static org.mockito.Mockito.lenient;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.hcl.elearning.dto.LoginRequestdto;
import com.hcl.elearning.dto.LoginResponsedto;
import com.hcl.elearning.entity.User;
import com.hcl.elearning.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(MockitoJUnitRunner.class)

@Slf4j
public class LoginSvcImplTest {

	@InjectMocks
	LoginServiceImpl loginServiceImpl;

	@Mock
	UserRepository userRepository;

	LoginResponsedto loginResponsedto;
	LoginRequestdto loginRequestdto;
	User user;

	public LoginResponsedto getLoginResponse() {
		loginResponsedto = new LoginResponsedto();
		loginResponsedto.setMessage("Login Success");
		loginResponsedto.setStatusCode(HttpStatus.OK.value());
		return loginResponsedto;
	}

	public LoginRequestdto getLoginRequest() {
		loginRequestdto = new LoginRequestdto();
		loginRequestdto.setUserMail("a@gmail.com");
		loginRequestdto.setPass("a");
		return loginRequestdto;
	}

	public User getUser() {
		user = new User();
		user.setUserMail("a@gmail.com");
		user.setPass("a");
		user.setRole(0);
		user.setPhone(123L);
		return user;
	}

	@Before

	public void setup() {
		loginResponsedto = getLoginResponse();
		loginRequestdto = getLoginRequest();
	}

	@Test
	public void loginTest() throws Exception {
		log.info("Entering into loginTest of LoginSvcImplTest");
		loginResponsedto = getLoginResponse();
		loginRequestdto = getLoginRequest();
		user = getUser();
		lenient().when(userRepository.findByUserMailAndPass("a@gmail.com", "a")).thenReturn(Optional.of(user));
		Optional<LoginResponsedto> response = loginServiceImpl.login(loginRequestdto);
		Assert.assertNotNull(response);
	}

	@Test(expected = Exception.class)
	public void loginTestNegative() throws Exception {
		log.info("Entering into loginTestNegative of LoginSvcImplTest");
		loginRequestdto.setPass("ant");
		lenient().when(userRepository.findByUserMailAndPass("a@gmail.com", "an")).thenReturn(Optional.ofNullable(null));
		loginServiceImpl.login(loginRequestdto);
	}

}

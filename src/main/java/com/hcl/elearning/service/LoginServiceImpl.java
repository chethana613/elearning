package com.hcl.elearning.service;


import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.elearning.constants.UserConstants;
import com.hcl.elearning.dto.LoginRequestdto;
import com.hcl.elearning.dto.LoginResponsedto;
import com.hcl.elearning.entity.User;
import com.hcl.elearning.exception.GeneralException;
import com.hcl.elearning.exception.UserException;
import com.hcl.elearning.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public Optional<LoginResponsedto> login(LoginRequestdto loginRequestdto) throws GeneralException {
		log.info("Entered into login of LogiServiceImpl");
		Optional<User> loginResponse= userRepository.findByUserMailAndPass(loginRequestdto.getUserMail(), loginRequestdto.getPass());

		if(!loginResponse.isPresent()) {
			throw new UserException(UserConstants.INVAID_LOGIN);
		}
		
			Optional<LoginResponsedto> optionalResponse;
			LoginResponsedto response= new LoginResponsedto();
			BeanUtils.copyProperties(loginResponse.get(),response);
			optionalResponse=Optional.of(response);
			return optionalResponse;
	}

}

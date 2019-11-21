package com.hcl.elearning.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.elearning.dto.LoginRequestdto;
import com.hcl.elearning.dto.LoginResponsedto;
import com.hcl.elearning.exception.GeneralException;

@Service
public interface LoginService {
	public Optional<LoginResponsedto> login(LoginRequestdto loginRequestdto) throws GeneralException;

}

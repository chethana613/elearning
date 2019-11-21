package com.hcl.elearning.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.elearning.constants.CourseConstants;
import com.hcl.elearning.constants.UserConstants;
import com.hcl.elearning.dto.LoginRequestdto;
import com.hcl.elearning.dto.LoginResponsedto;
import com.hcl.elearning.dto.UserCourseRequestdto;
import com.hcl.elearning.dto.UserCourseResponsedto;
import com.hcl.elearning.dto.UserEnrolledCourseResponse;
import com.hcl.elearning.dto.UserEnrolledCourseResponsedto;
import com.hcl.elearning.exception.CourseException;
import com.hcl.elearning.exception.GeneralException;
import com.hcl.elearning.exception.UserException;
import com.hcl.elearning.service.LoginService;
import com.hcl.elearning.service.UserCourseService;


@RestController
@RequestMapping("/eLearningUser")
public class UserController {
	
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserCourseService userCourseService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<Optional<LoginResponsedto>> login(@RequestBody LoginRequestdto  loginRequestdto) throws GeneralException{
		logger.info("Inside Login method of User Controller");
		Optional<LoginResponsedto> response=loginService.login(loginRequestdto);
		if(!response.isPresent()) {
			throw new UserException(UserConstants.INVAID_LOGIN);
		}
		response.get().setMessage(UserConstants.LOGIN_SUCCESS);
		response.get().setStatusCode(HttpStatus.OK.value());
		return  new ResponseEntity<>(response, HttpStatus.OK);		
	}
	
	@PostMapping(value = "/enrollCourse")
	public ResponseEntity<Optional<UserCourseResponsedto>> enrollCourse(@RequestBody UserCourseRequestdto  userCourseRequestdto) throws CourseException{
		logger.info("Inside enrollCourse method of User Controller");
		Optional<UserCourseResponsedto> response=userCourseService.enrollCourse(userCourseRequestdto);
		if(!response.isPresent()) {
			throw new CourseException(UserConstants.ENROLLED_COURSE);
		}
		response.get().setMessage(CourseConstants.SUCCESS);
		response.get().setStatusCode(HttpStatus.OK.value());		
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@GetMapping(value = "/viewAllUserEnrolledCourses/{userId}")
	public ResponseEntity<UserEnrolledCourseResponsedto> getAllUserEnrolledCourses(@PathVariable Integer userId){
		logger.info("Inside getAllEnrolledCourses method of User Controller");
		Optional<List<UserEnrolledCourseResponse>> response=userCourseService.getAllUserEnrolledCourses(userId);
		UserEnrolledCourseResponsedto userEnrolledCourseResponsedto= new UserEnrolledCourseResponsedto();
		if(!response.isPresent()) {
			userEnrolledCourseResponsedto.setMessage(UserConstants.NO_ENROLLED_COURSE);
			userEnrolledCourseResponsedto.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(userEnrolledCourseResponsedto, HttpStatus.OK);
		}
		userEnrolledCourseResponsedto.setMessage(CourseConstants.SUCCESS);
		userEnrolledCourseResponsedto.setStatusCode(HttpStatus.OK.value());
		userEnrolledCourseResponsedto.setListOfenrolledCourses(response.get());
		return new ResponseEntity<>(userEnrolledCourseResponsedto, HttpStatus.OK);	
	}
	
}

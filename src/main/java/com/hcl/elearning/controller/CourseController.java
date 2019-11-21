package com.hcl.elearning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.elearning.constants.CourseConstants;
import com.hcl.elearning.dto.CourseResponse;
import com.hcl.elearning.dto.CourseResponsedto;
import com.hcl.elearning.exception.CourseException;
import com.hcl.elearning.service.CourseService;

@RestController
@RequestMapping("/eLearningCourse")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@GetMapping(value = "/viewAvailableCourses")
	public ResponseEntity<CourseResponsedto> getAllAvailableCourses(){	
		Optional<List<CourseResponse>> response= courseService.getAllAvailableCourses();
		CourseResponsedto courseResponsedto= new CourseResponsedto();
		if(!response.isPresent()) {
			courseResponsedto.setMessage(CourseConstants.NO_COURSES_FOUND);
			courseResponsedto.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(courseResponsedto, HttpStatus.EXPECTATION_FAILED);	
		}
		
		courseResponsedto.setMessage(CourseConstants.SUCCESS);
		courseResponsedto.setStatusCode(HttpStatus.OK.value());
		courseResponsedto.setListOfCourses(response.get());
		
		return new ResponseEntity<>(courseResponsedto, HttpStatus.OK);		
		
	}

	@GetMapping(value = "/viewCourseById/{courseId}")
	public ResponseEntity<CourseResponse> getCourseById(@PathVariable Integer courseId) throws CourseException{
		CourseResponse courseResponse=courseService.getCourseById(courseId);		
		return new ResponseEntity<>(courseResponse, HttpStatus.OK);
	}
}

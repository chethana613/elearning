package com.hcl.elearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.elearning.constants.CourseConstants;
import com.hcl.elearning.dto.CourseResponse;
import com.hcl.elearning.entity.Course;
import com.hcl.elearning.exception.CourseException;
import com.hcl.elearning.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CouseServiceImpl implements CourseService{
	@Autowired
	CourseRepository courseRepository;
	
	
	public Optional<List<CourseResponse>> getAllAvailableCourses(){
		
		log.info("Entering into getAllAvailableCourses of CouseServiceImpl");
		List<Course> courses=courseRepository.findAll();
		List<CourseResponse> response= new ArrayList<>();
		for(Course course:courses) {
			CourseResponse courseResponse = new CourseResponse();
			BeanUtils.copyProperties(course, courseResponse);
			response.add(courseResponse);
		}
			
		return Optional.of(response);
		
	}
	
	public CourseResponse getCourseById(int courseId) throws CourseException {
		log.info("Entering into getCourseById of CouseServiceImpl");
		Optional<Course> courseOptionalDetails=courseRepository.findById(courseId);
		if(!courseOptionalDetails.isPresent()) {
			throw new CourseException(CourseConstants.INVAID_COURSE);
		}
		Course courseDetails= courseOptionalDetails.get();
		CourseResponse courseResponse= new CourseResponse();
		BeanUtils.copyProperties(courseDetails, courseResponse);
		return courseResponse;
		
	}
}

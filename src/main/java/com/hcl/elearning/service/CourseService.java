package com.hcl.elearning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.elearning.dto.CourseResponse;
import com.hcl.elearning.exception.CourseException;

@Service
public interface CourseService {

	public Optional<List<CourseResponse>> getAllAvailableCourses();
	public CourseResponse getCourseById(int courseId)throws CourseException;
}

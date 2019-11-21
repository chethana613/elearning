package com.hcl.elearning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hcl.elearning.dto.UserCourseRequestdto;
import com.hcl.elearning.dto.UserCourseResponsedto;
import com.hcl.elearning.dto.UserEnrolledCourseResponse;
import com.hcl.elearning.exception.CourseException;

@Service
public interface UserCourseService {
	public Optional<UserCourseResponsedto> enrollCourse(UserCourseRequestdto userCourseRequestdto) throws CourseException;
	public Optional<List<UserEnrolledCourseResponse>> getAllUserEnrolledCourses(int userId);
}

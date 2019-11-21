package com.hcl.elearning.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.elearning.constants.CourseConstants;
import com.hcl.elearning.constants.UserConstants;
import com.hcl.elearning.dto.UserCourseRequestdto;
import com.hcl.elearning.dto.UserCourseResponsedto;
import com.hcl.elearning.dto.UserEnrolledCourseResponse;
import com.hcl.elearning.entity.Course;
import com.hcl.elearning.entity.User;
import com.hcl.elearning.entity.UserCourse;
import com.hcl.elearning.exception.CourseException;
import com.hcl.elearning.exception.UserException;
import com.hcl.elearning.repository.CourseRepository;
import com.hcl.elearning.repository.UserCourseRepository;
import com.hcl.elearning.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserCourseSvcImpl implements UserCourseService {

	@Autowired
	UserCourseRepository userCourseRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	UserRepository userRepository;

	public Optional<UserCourseResponsedto> enrollCourse(UserCourseRequestdto userCourseRequestdto)
			throws CourseException {
		log.info("Entering into enrollCourse of UserCourseSvcImpl");
		Optional<Course> courseValidation = courseRepository.findById(userCourseRequestdto.getCourseId());
		Optional<User> userValidation = userRepository.findById(userCourseRequestdto.getUserId());
		
		if (!userValidation.isPresent()) {
			throw new UserException(UserConstants.INVAID_USER);
		} else if (!courseValidation.isPresent()) {
			throw new CourseException(CourseConstants.INVAID_COURSE);
		} else {
			Optional<UserCourse> checkForEnrollment = userCourseRepository.findByUserIdAndCourseId(userValidation.get(), courseValidation.get());
			if (checkForEnrollment.isPresent()) {
				log.error(UserConstants.ENROLLED_COURSE);
				throw new CourseException(UserConstants.ENROLLED_COURSE);
			}
			UserCourse userCourse = new UserCourse();
			userCourse.setCourseId(courseValidation.get());
			userCourse.setUserId(userValidation.get());

			userCourse.setCourseStatus(UserConstants.NOT_STARTED);
			userCourse.setEnrolledOn(LocalDate.now());
			userCourse.setExpiryDate(LocalDate.now().plusDays(30));
			userCourseRepository.save(userCourse);
			UserCourseResponsedto userCourseResponsedto = new UserCourseResponsedto();
			return Optional.of(userCourseResponsedto);
		}
	}

	public Optional<List<UserEnrolledCourseResponse>> getAllUserEnrolledCourses(int userId) {
		log.info("Entering into getAllEnrolledCourses of UserCourseSvcImpl");
		Optional<User> userValidation = userRepository.findById(userId);
		if(!userValidation.isPresent()) {
			throw new UserException(UserConstants.INVAID_USER);
		}
		List<UserCourse> lists = userCourseRepository.findAllByUserId(userValidation.get());
		List<UserEnrolledCourseResponse> enrolledList = new ArrayList<>();
		lists.forEach(list -> {
			UserEnrolledCourseResponse userEnrolledCourseResponse = new UserEnrolledCourseResponse();
			BeanUtils.copyProperties(list, userEnrolledCourseResponse);
			enrolledList.add(userEnrolledCourseResponse);
		});
		return Optional.of(enrolledList);

	}
}

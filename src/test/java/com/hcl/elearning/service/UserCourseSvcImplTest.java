package com.hcl.elearning.service;

import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class UserCourseSvcImplTest {
	
	@Mock
	UserCourseRepository userCourseRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	CourseRepository courseRepository;
	
	
	@InjectMocks
	UserCourseSvcImpl userCourseSvcImpl;
	
	
	List<UserCourse> userCourseList= new ArrayList<>();
	UserCourse userCourse= new UserCourse();
	User user= new User();
	Course course= new Course();
	UserCourseRequestdto userCourseRequestdto= new UserCourseRequestdto();

	@Before
	public void setup() {
		
		log.info("Entering into  Before setup method of UserCourseSvcImplTest");
		user.setUserId(1);
		user.setUserMail("a@gmail.com");
		user.setPass("a");
		user.setPhone(123L);
		user.setRole(1);
		userCourse.setUserId(user);	
		userCourseList.add(userCourse);		
	}
	
	@Test
	public void getAllUserEnrolledCourses() throws UserException{
		log.info("Entering into  getAllUserEnrolledCourses method of UserCourseSvcImplTest");
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		lenient().when(userCourseRepository.findAllByUserId(Mockito.any())).thenReturn(userCourseList);
		Optional<List<UserEnrolledCourseResponse>> respose=userCourseSvcImpl.getAllUserEnrolledCourses(user.getUserId());
		Assert.assertNotNull(respose);		
	}
	
	@Test(expected=UserException.class)
	public void getAllUserEnrolledCoursesNegative() throws UserException{
		user.setUserId(99);
		log.info("Entering into  getAllUserEnrolledCoursesNegative method of UserCourseSvcImplTest");
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));
		lenient().when(userCourseRepository.findAll()).thenReturn(userCourseList);
		Optional<List<UserEnrolledCourseResponse>> respose=userCourseSvcImpl.getAllUserEnrolledCourses(user.getUserId());
		Assert.assertNotNull(respose);		
	}
	
	@Test(expected=UserException.class)
	public void enrollCourseWithInvalidUser() throws CourseException {
		log.info("Entering into  enrollCourse method of UserCourseSvcImplTest");
		user.setUserId(99);
		UserCourseRequestdto userCourseRequestdto= new UserCourseRequestdto();
		userCourseRequestdto.setUserId(1);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));
		userCourseSvcImpl.enrollCourse(userCourseRequestdto);
	}
	
	@Test(expected=CourseException.class)
	public void enrollCourseWithInvalidCourse() throws CourseException {
		log.info("Entering into  enrollCourse method of UserCourseSvcImplTest");
		userCourseRequestdto.setUserId(1);
		userCourseRequestdto.setCourseId(99);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(user));
		Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));
		userCourseSvcImpl.enrollCourse(userCourseRequestdto);
	}
	
	@Test(expected=CourseException.class)
	public void enrollCourseWithValidRecord() throws CourseException {
		log.info("Entering into  enrollCourseWithValidRecord method of UserCourseSvcImplTest");
		userCourseRequestdto.setUserId(1);
		userCourseRequestdto.setCourseId(1);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(user));
		Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(course));
		lenient().when(userCourseRepository.findByUserIdAndCourseId(Mockito.any(), Mockito.any())).thenReturn(Optional.of(userCourse));
		userCourseSvcImpl.enrollCourse(userCourseRequestdto);
	}
	
	@Test
	public void enrollCourse() throws CourseException {
		log.info("Entering into  enrollCourseWithValidRecord method of UserCourseSvcImplTest");
		userCourseRequestdto.setUserId(2);
		userCourseRequestdto.setCourseId(1);
		Mockito.when(userRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(user));
		Mockito.when(courseRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(course));
		lenient().when(userCourseRepository.findByUserIdAndCourseId(Mockito.any(), Mockito.any())).thenReturn(Optional.ofNullable(null));
		Optional<UserCourseResponsedto> respose=userCourseSvcImpl.enrollCourse(userCourseRequestdto);
		Assert.assertNotNull(respose);
	}

}

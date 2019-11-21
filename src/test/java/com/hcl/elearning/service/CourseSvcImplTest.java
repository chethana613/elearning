package com.hcl.elearning.service;

import java.time.LocalTime;
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

import com.hcl.elearning.dto.CourseResponse;
import com.hcl.elearning.entity.Course;
import com.hcl.elearning.exception.CourseException;
import com.hcl.elearning.repository.CourseRepository;

import lombok.extern.slf4j.Slf4j;

@RunWith(MockitoJUnitRunner.class)

@Slf4j
public class CourseSvcImplTest {
	
	@Mock
	CourseRepository CourseRepository;
	
	@InjectMocks
	CouseServiceImpl courseServiceImpl;
	
	List<Course> courses= new ArrayList<>();
	Course course= new Course();
	
	
	
	@Before
	public void setup() {
		log.info("Entering into Before Setup of CourseSvcImplTest");
		LocalTime duration= LocalTime.parse("02:00:00");
		course.setCourseId(1);
		course.setCourseCategory("development");
		course.setCourseDuration(duration);
		course.setCourseName("spring");
		course.setPrice(1500);
		courses.add(course);
	}

	@Test
	public void getAllAvailableCoursesTest() {
		log.info("Entering into getAllAvailableCoursesTest of CourseSvcImplTest");
		Mockito.when(CourseRepository.findAll()).thenReturn(courses);
		Optional<List<CourseResponse>> courseResponsedto=courseServiceImpl.getAllAvailableCourses();
		Assert.assertNotNull(courseResponsedto);
	}
	
	@Test
	public void getCourseByIdTest() throws CourseException {
		log.info("Entering into getCourseByIdTest of CourseSvcImplTest");		
		Mockito.when(CourseRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(course));
		CourseResponse response=courseServiceImpl.getCourseById(1);
		Assert.assertNotNull(response);	
	}
	
	@Test(expected = CourseException.class)
	public void getCourseByIdTestNegative() throws CourseException {
		log.info("Entering into getCourseByIdTestNegative of CourseSvcImplTest");	
		Mockito.when(CourseRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(null));
		courseServiceImpl.getCourseById(11);
	}
}

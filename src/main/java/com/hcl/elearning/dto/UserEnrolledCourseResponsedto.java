package com.hcl.elearning.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEnrolledCourseResponsedto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8704969984089383040L;
	private String message;
	private Integer statusCode;
	private List<UserEnrolledCourseResponse> listOfenrolledCourses;
}

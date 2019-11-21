package com.hcl.elearning.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CourseResponsedto implements Serializable{
	
	private static final long serialVersionUID = -7027593953888156435L;
	private String message;
	private Integer statusCode;
	private List<CourseResponse> listOfCourses;

}

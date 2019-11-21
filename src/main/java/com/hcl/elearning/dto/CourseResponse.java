package com.hcl.elearning.dto;

import java.io.Serializable;
import java.time.LocalTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CourseResponse implements Serializable {

	private static final long serialVersionUID = -7907898099972393160L;
	private Integer courseId;
	private String courseName;
	private String courseCategory;
	private Integer price;
	private LocalTime courseDuration;
	
}

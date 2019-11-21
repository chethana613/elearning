package com.hcl.elearning.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="User_Courses")
@Getter
@Setter
public class UserCourse {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userCourseId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "courseId", nullable = false)
	private Course courseId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userId", nullable = false)
	private User userId;
	
	private LocalDate enrolledOn;
	private LocalDate expiryDate;
	private String courseStatus;
	
	
	
}

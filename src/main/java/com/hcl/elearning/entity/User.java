package com.hcl.elearning.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="User")
@Getter
@Setter
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	@Column(unique = true)
	private String userMail;
	private String pass;
	private Long phone;
	private Integer role;
	
	@OneToMany(mappedBy="userId", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<UserCourse> userCourseList;
	
}

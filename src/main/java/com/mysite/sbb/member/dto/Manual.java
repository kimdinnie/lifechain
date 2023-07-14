package com.mysite.sbb.member.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Manual {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String manualType;
	
	@Column(length = 200)
	private String subject;
	
	@Column(length = 200)
	private String summary;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	@Column(length = 200)
	private String manualPic;


}

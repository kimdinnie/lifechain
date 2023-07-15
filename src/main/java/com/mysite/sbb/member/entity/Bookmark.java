package com.mysite.sbb.member.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.mysite.sbb.member.entity.Manual;
import com.mysite.sbb.member.entity.Member;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Bookmark {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member author;
	
	@ManyToOne
	@JoinColumn(name = "manual_id")
	private Manual manual;
	
	@CreationTimestamp
	private LocalDateTime createDate;

}

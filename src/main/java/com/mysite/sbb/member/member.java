package com.mysite.sbb.member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.comment.Comment;
import com.mysite.sbb.question.Question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class member {
	/* 기본키(primary key) */
	@Id
	
	/* 자동증가(Auto Increment) */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String memberId;
	
	@Column(unique = true)
	private String memberNick;
	
	private LocalDateTime memberDate;
	
	private String memberPw;
	
	private String memberTel;

	@OneToMany(mappedBy = "author")
    private List<Question> question = new ArrayList<>();

	@OneToMany(mappedBy = "author")
	private List<Answer> answer = new ArrayList<>();
	
	@OneToMany(mappedBy = "author")
	private List<Comment> comment = new ArrayList<>();
	
	}

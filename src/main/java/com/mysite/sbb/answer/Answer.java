package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.comment.Comment;
import com.mysite.sbb.member.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {
	/* 기본키(primary key) */
	@Id
	
	/* 자동증가(Auto Increment) */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	/* N:1 ForeignKey */
	@ManyToOne
	private Question question;
	
	@OneToMany(mappedBy = "answer")
	private List<Comment> commentList;
	
	@ManyToOne
	private member author;
	
	private LocalDateTime modifyDate;

	@ManyToMany
	Set<member> voter;
	// 추천 중복을 피하기 위해 Set
	
}

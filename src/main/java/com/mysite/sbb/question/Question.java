package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.comment.Comment;
import com.mysite.sbb.member.member;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	/* 기본키(primary key) */
	@Id
	
	/* 자동증가(Auto Increment) */
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	@OneToMany(mappedBy = "question")
	private List<Comment> commentList;
	
	@ManyToOne
	private member author;
	
	private LocalDateTime modifyDate;
	
	private Integer views;
	
	@ManyToMany
	Set<member> voter;
	// 추천 중복을 피하기 위해 Set
	
	public void setAuthor(member author) {
        this.author = author;
        author.getQuestion().add(this);
    }
	
}



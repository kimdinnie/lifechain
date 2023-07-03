package com.mysite.sbb.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.sbb.member.dto.Answer;
import com.mysite.sbb.member.dto.Question;

public interface AnswerRepository extends JpaRepository<Answer, Integer>{
	Page<Answer> findAllByQuestion(Question question, Pageable pageable);
}

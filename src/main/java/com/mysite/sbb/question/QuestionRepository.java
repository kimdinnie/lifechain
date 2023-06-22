package com.mysite.sbb.question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);
    Page<Question> findAll(Pageable pageable);
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);
    
    @Query("select "
            + "distinct q "
            + "from Question q " 
            + "left outer join member u1 on q.author=u1 "
            + "left outer join Answer a on a.question=q "
            + "left outer join member u2 on a.author=u2 "
            + "where "
            + "   q.subject like %:kw% "
            + "   or q.content like %:kw% "
            + "   or u1.memberId like %:kw% "
            + "   or a.content like %:kw% "
            + "   or u2.memberId like %:kw% ")
    Page<Question> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
	
	@Modifying
    @Query(value = "UPDATE Question q SET q.views=q.views + 1 where q.id = :id")
    @Transactional
    Integer updateView(@Param("id") Integer id);

}
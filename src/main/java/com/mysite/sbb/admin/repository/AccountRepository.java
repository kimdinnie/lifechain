package com.mysite.sbb.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.sbb.member.dto.Member;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Member,Long> {
    Optional<Member> findById(Long id);
}

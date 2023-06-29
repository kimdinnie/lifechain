package com.mysite.sbb.admin.repository;

import com.mysite.sbb.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Member,Long> {
    Optional<Member> findById(Long id);
}

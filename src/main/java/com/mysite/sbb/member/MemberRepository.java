package com.mysite.sbb.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<member, Long>{
	Optional<member> findBymemberId(String memberId);
}

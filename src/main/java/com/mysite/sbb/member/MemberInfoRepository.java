package com.mysite.sbb.member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {
    MemberInfo findByMemberId(String memberId);
}

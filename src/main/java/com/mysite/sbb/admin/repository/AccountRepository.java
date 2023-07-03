package com.mysite.sbb.admin.repository;

<<<<<<< HEAD
import com.mysite.sbb.member.Member;
import com.mysite.sbb.member.MemberInfo;
=======
>>>>>>> 155196765207b3036d40917743de7cb6424dce00
import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.sbb.member.dto.Member;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Member, Long> {

}

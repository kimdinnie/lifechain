package com.mysite.sbb.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mysite.sbb.common.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    MemberInfoRepository memberInfoRepository;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member create(String memberId, String memberPw, String memberNick) {
        Member member = Member.builder()
                .memberId(memberId)
                .memberPw(passwordEncoder.encode(memberPw))
                .memberNick(memberNick)
                .memberStatus(MemberStatus.ACTIVE)
                .build();
        this.memberRepository.save(member);

        member.setMemberStatus(MemberStatus.ACTIVE);

        MemberInfo memberInfo = new MemberInfo(); //회원가입할 때 memberInfo 테이블에 null값으로 저장
        memberInfo.setId(member.getId()); // 모든 멤버 변수가 null값인 객체 생성 후 PK 값 설정
        this.memberInfoRepository.save(memberInfo);

        return member;
    }

    public Member getMember(String memberId) {
        Optional<Member> member = this.memberRepository.findBymemberId(memberId);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public void memberModify(Member member, String memberId, String memberNick) {
        member.setMemberId(memberId);
        member.setMemberNick(memberNick);
        this.memberRepository.save(member);
    }

    public Member getMemberByMemberNick(String memberNick) {
        Member member = new Member();
        member.setMemberNick(memberNick);
        return member;
    }


}

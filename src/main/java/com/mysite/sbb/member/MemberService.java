package com.mysite.sbb.member;

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

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

//    public Member create(String memberId, String memberPw, String memberNick) {
//        Member member = new Member();
//        member.setMemberId(memberId);
//        member.setMemberPw(passwordEncoder.encode(memberPw));
//        member.setMemberNick(memberNick);
//        this.memberRepository.save(member);
//        return member;
//    }

    public Member create(String memberId,String memberPw,String memberNick){
        Member member = Member.builder()
                .memberId(memberId)
                .memberPw(passwordEncoder.encode(memberPw))
                .memberNick(memberNick)
                .build();
        this.memberRepository.save(member);
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

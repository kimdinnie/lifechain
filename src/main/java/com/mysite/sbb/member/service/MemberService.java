package com.mysite.sbb.member.service;

import com.mysite.sbb.member.entity.MemberInfo;
import com.mysite.sbb.member.entity.MemberStatus;
import com.mysite.sbb.member.mail.EmailException;
import com.mysite.sbb.member.repository.MemberInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.mysite.sbb.member.entity.Member;
import com.mysite.sbb.member.mail.TempPasswordMail;
import com.mysite.sbb.member.repository.MemberRepository;
import com.mysite.sbb.common.CommonUtil;
import com.mysite.sbb.common.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    @Autowired
    MemberInfoRepository memberInfoRepository;

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TempPasswordMail tempPasswordMail;
    private final CommonUtil commonUtil;


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

    @Transactional
    public void modifyPassword(String memberId) throws EmailException {
        String tempPassword = commonUtil.createTempPassword();
        Member member = memberRepository.findBymemberId(memberId)
                .orElseThrow(() -> new DataNotFoundException("해당 이메일의 유저가 없습니다."));
        member.setMemberPw(passwordEncoder.encode(tempPassword));
        memberRepository.save(member);
        tempPasswordMail.sendSimpleMessage(memberId, tempPassword);
    }

}

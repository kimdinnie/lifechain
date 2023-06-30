package com.mysite.sbb.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.mysite.sbb.member.dto.Member;
import com.mysite.sbb.member.mail.EmailException;
import com.mysite.sbb.member.mail.TempPasswordMail;
import com.mysite.sbb.common.CommonUtil;
import com.mysite.sbb.common.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TempPasswordMail tempPasswordMail;
    private final CommonUtil commonUtil;

		
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

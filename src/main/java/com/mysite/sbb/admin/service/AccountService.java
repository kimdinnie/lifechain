package com.mysite.sbb.admin.service;

import com.mysite.sbb.admin.repository.AccountRepository;
import com.mysite.sbb.common.DataNotFoundException;
import com.mysite.sbb.member.dto.MemberDto;
import com.mysite.sbb.member.entity.Member;
import com.mysite.sbb.member.entity.MemberInfo;
import com.mysite.sbb.member.repository.MemberRepository;
import com.mysite.sbb.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    public List<MemberDto> getMemberList() { //전체회원 가져오기
        List<Member> members = memberRepository.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();

        for (Member member : members) {
            MemberDto memberDto = MemberDto.builder()
                    .id(member.getId())
                    .memberId(member.getMemberId())
                    .memberNick(member.getMemberNick())
                    .memberDate(member.getCreatedDate())
                    .memberStatus(member.getMemberStatus())
                    .lastLoginDate(member.getUpdatedDate())
                    .build();
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    public Member getMember(Long id) { //id로 멤버 찾기
        Optional<Member> member = this.accountRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public void deleteAccount(Long id) { //관리자의 회원삭제
        accountRepository.deleteById(id);
    } //회원삭제

    public MemberInfo getMemberInfoByMember(Member member) { //member로 memberInfo 찾기
        if (member != null) {
            MemberInfo memberInfo = member.getMemberInfo();
            if (memberInfo != null) {
                return memberInfo;
            }
        }
        return null;
    }


}
package com.mysite.sbb.admin.service;

import com.mysite.sbb.admin.repository.AccountRepository;
import com.mysite.sbb.common.DataNotFoundException;
<<<<<<< HEAD
import com.mysite.sbb.member.*;
=======
import com.mysite.sbb.member.dto.Member;
import com.mysite.sbb.member.dto.MemberDto;
import com.mysite.sbb.member.repository.MemberRepository;

>>>>>>> 155196765207b3036d40917743de7cb6424dce00
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
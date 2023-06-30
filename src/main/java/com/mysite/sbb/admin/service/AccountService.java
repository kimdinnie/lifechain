package com.mysite.sbb.admin.service;

import com.mysite.sbb.admin.repository.AccountRepository;
import com.mysite.sbb.common.DataNotFoundException;
import com.mysite.sbb.member.dto.Member;
import com.mysite.sbb.member.dto.MemberDto;
import com.mysite.sbb.member.repository.MemberRepository;

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
                    .lastLoginDate(member.getUpdatedDate())
                    .build();
            memberDtoList.add(memberDto);
        }
        return memberDtoList;
    }

    public Member getMember(Long id) { //회원 아이디 정보 가져오기
        Optional<Member> member = this.memberRepository.findById(id);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public void deleteAccount(Long id) { //회원탈퇴
        accountRepository.deleteById(id);
    }
}

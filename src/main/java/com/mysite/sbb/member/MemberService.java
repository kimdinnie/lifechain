package com.mysite.sbb.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public member create(String memberId, String memberPw, String memberNick) {
		member member = new member();
		member.setMemberId(memberId);
		member.setMemberPw(passwordEncoder.encode(memberPw));
		member.setMemberNick(memberNick);
		this.memberRepository.save(member);
		return member;
	}
	
	public member getMember(String memberId) {
		Optional<member> member = this.memberRepository.findBymemberId(memberId);
		if(member.isPresent()) {
			return member.get();
		} else {
			throw new DataNotFoundException("member not found");
		}
	}
	
	public void memberModify(member member, String memberId, String memberNick) {
		member.setMemberId(memberId);
		member.setMemberNick(memberNick);

	    this.memberRepository.save(member);
	}
	
	public member getMemberByMemberNick(String memberNick) {
	    member member = new member();
	    member.setMemberNick(memberNick);
	    

	    return member;
	}
	


	
		
}

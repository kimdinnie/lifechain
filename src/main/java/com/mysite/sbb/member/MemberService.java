package com.mysite.sbb.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.mysite.sbb.CommonUtil;
import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.EmailException;
import com.mysite.sbb.member.mail.TempPasswordMail;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
    private final TempPasswordMail tempPasswordMail;
    private final CommonUtil commonUtil;
	
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
	
    @Transactional
    public void modifyPassword(String memberId) throws EmailException {
        String tempPassword = commonUtil.createTempPassword();
        member member = memberRepository.findBymemberId(memberId)
            .orElseThrow(() -> new DataNotFoundException("해당 이메일의 유저가 없습니다."));
        member.setMemberPw(passwordEncoder.encode(tempPassword));
        memberRepository.save(member);
        tempPasswordMail.sendSimpleMessage(memberId, tempPassword);
    }

		
}

package com.mysite.sbb.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private long id;
    private String memberId; //이메일 주소 , 회원가입 시 아이디로 사용
    private String memberNick;
    private Date memberDate; //회원가입
    private Date lastLoginDate; //마지막 접속일
    private String memberPw;
    private String memberTel;

    @Builder
    public MemberDto(long id, String memberId, String memberNick, LocalDateTime memberDate, LocalDateTime lastLoginDate, String memberPw, String memberTel) {
        this.id = id;
        this.memberId = memberId;
        this.memberNick = memberNick;
        this.memberDate = convertToDate(memberDate);
        this.lastLoginDate = convertToDate(lastLoginDate);
        this.memberPw = memberPw;
        this.memberTel = memberTel;
    }

    private Date convertToDate(LocalDateTime localDateTime) {
        return localDateTime != null ? Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()) : null;
    }

}

package com.mysite.sbb.member.dto;

import java.util.ArrayList;
import java.util.List;

import com.mysite.sbb.common.BaseEntity;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor //기본 생성자를 생성
public class Member extends BaseEntity {
    /* 기본키(primary key) */
    @Id

    /* 자동증가(Auto Increment) */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String memberId;

    @Column(unique = true)
    private String memberNick;

    private String memberPw;

    @Column(unique = true) //전화번호 중복 방지 //회원가입에서 안쓸거라면 지우는게 좋을 것 같아용.
    private String memberTel;

    @OneToMany(mappedBy = "author")
    private List<Question> question = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Answer> answer = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Comment> comment = new ArrayList<>();

    @Builder
    public Member(String memberId, String memberNick, String memberPw) {
        this.memberId = memberId;
        this.memberNick = memberNick;
        this.memberPw = memberPw;
    }
}

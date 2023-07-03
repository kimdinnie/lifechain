package com.mysite.sbb.member;

import java.util.ArrayList;
import java.util.List;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.comment.Comment;
import com.mysite.sbb.common.BaseEntity;
import com.mysite.sbb.question.Question;

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
    private Long id; // Member 엔티티 PK와 관련된 MemberInfo PK값 (동일한 값을 사용)

    @Column(unique = true)
    private String memberId;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MemberInfo memberInfo;

    @Column(unique = true)
    private String memberNick;

    private String memberPw;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_status")
    private MemberStatus memberStatus;

    @OneToMany(mappedBy = "author")
    private List<Question> question = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Answer> answer = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<Comment> comment = new ArrayList<>();

    @Builder
    public Member(String memberId, String memberNick, String memberPw, MemberStatus memberStatus) {
        this.memberId = memberId;
        this.memberNick = memberNick;
        this.memberPw = memberPw;
        this.memberStatus = memberStatus;
    }
}

package com.mysite.sbb.member;

import com.mysite.sbb.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class MemberInfo extends BaseEntity {
    // MemberInfo는 Member의 기본키를 사용하므로 Serializable 구현
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String zipcode;
    private String address1;
    private String address2; //주소 api 연결해서 쓰고
    private String memberImg; //이미지 주소
    private String memberIntroduce; //자기소개, 한마디
    @Column(unique = true) //전화번호 중복 방지
    private String memberTel;

}

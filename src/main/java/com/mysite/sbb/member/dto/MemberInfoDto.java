package com.mysite.sbb.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor //생성자 자동추가
public class MemberInfoDto {
    private String zipcode;
    private String address1;
    private String address2; //주소 api 연결해서 쓰고
    private String memberImg; //이미지 주소
    private String memberIntroduce; //자기소개, 한마디
    private String memberTel;
    @Builder
    public MemberInfoDto(String zipcode, String address1, String address2, String memberImg, String memberIntroduce, String memberTel) {
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address2 = address2;
        this.memberImg = memberImg;
        this.memberIntroduce = memberIntroduce;
        this.memberTel = memberTel;
    }
}

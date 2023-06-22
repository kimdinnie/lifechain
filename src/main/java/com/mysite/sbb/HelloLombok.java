package com.mysite.sbb;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//의존성 주입시 사용
@RequiredArgsConstructor
@Getter
public class HelloLombok {
	//final을 사용함으로써 한번 설정한 값을 변경할 수 없게 만듦.
	private final String hello;
	private final int lombok;
	
	public static void main(String[] args) {
		HelloLombok helloLombok = new HelloLombok("헬로", 5);
		
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());
	}

}

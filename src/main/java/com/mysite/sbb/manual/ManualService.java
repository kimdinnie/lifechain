package com.mysite.sbb.manual;

import java.util.List;

import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManualService {
	
	private final ManualRepository manualRepository;
	
	public List<Manual> getList() {
		return this.manualRepository.findAll();
	}


}

package com.mysite.sbb.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.sbb.member.dto.Manual;
import com.mysite.sbb.member.repository.ManualRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManualService {
	
	private final ManualRepository manualRepository;
	
	public List<Manual> getList() {
		return this.manualRepository.findAll();
	}


}

package com.mysite.sbb.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.member.entity.Manual;
import com.mysite.sbb.common.DataNotFoundException;
import com.mysite.sbb.member.repository.BookmarkRepository;
import com.mysite.sbb.member.repository.ManualRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManualService {
	
	private final ManualRepository manualRepository;
	private final BookmarkRepository bookmarkRepository;
	
	public List<Manual> getList() {
		return this.manualRepository.findAll();
	}
	
	public Manual getManual(Integer id) {
		Optional<Manual> manual = this.manualRepository.findById(id);
		if(manual.isPresent()) {
			return manual.get();
		} else {
			throw new DataNotFoundException("manual not found");
		}
	}
	


}

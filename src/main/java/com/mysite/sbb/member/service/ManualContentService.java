package com.mysite.sbb.member.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.mysite.sbb.common.DataNotFoundException;
import com.mysite.sbb.member.dto.Manual;
import com.mysite.sbb.member.dto.ManualContent;
import com.mysite.sbb.member.repository.ManualContentRepository;
import com.mysite.sbb.member.repository.ManualRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManualContentService {
	
	private final ManualRepository manualRepository;
	private final ManualContentRepository manualContentRepository;
	
	public List<ManualContent> getList(Manual manual) {
		return this.manualContentRepository.findAllByManual(manual);
	}
	
	public List<ManualContent> getManualList(ManualContent manualContent) {
		return manualContentRepository.findByManual(manualContent);
	}
	
	public List<ManualContent> getManualContentList() {
	    return manualContentRepository.findAll();
	}
	
	public List<ManualContent> getManualContentWithManual() {
	    return manualContentRepository.findManualContentWithManual();
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

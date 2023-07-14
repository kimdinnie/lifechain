package com.mysite.sbb.member.repository;

import java.util.List;

import com.mysite.sbb.member.entity.Manual;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mysite.sbb.member.dto.ManualContent;

public interface ManualContentRepository extends JpaRepository<ManualContent, Integer>{
	List<ManualContent> findAllByManual(Manual manual);
	List<ManualContent> findByManual(ManualContent manualContent);
    @Query("SELECT m FROM ManualContent m JOIN FETCH m.manual")
    List<ManualContent> findManualContentWithManual();

}
package com.mysite.sbb.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.sbb.member.dto.Manual;

public interface ManualRepository extends JpaRepository<Manual, Integer>{
	

}
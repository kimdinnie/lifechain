package com.mysite.sbb.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.sbb.member.dto.Bookmark;
import com.mysite.sbb.member.dto.Manual;
import com.mysite.sbb.member.dto.Member;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
	void deleteByAuthorAndManual(Integer manualId, Integer memberId);
}
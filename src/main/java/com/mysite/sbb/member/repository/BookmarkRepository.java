package com.mysite.sbb.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.sbb.member.entity.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
	void deleteByAuthorAndManual(Integer manualId, Integer memberId);
}
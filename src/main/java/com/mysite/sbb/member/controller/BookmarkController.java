package com.mysite.sbb.member.controller;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.member.dto.Bookmark;
import com.mysite.sbb.member.dto.Manual;
import com.mysite.sbb.member.dto.Member;
import com.mysite.sbb.member.repository.BookmarkRepository;
import com.mysite.sbb.member.service.ManualService;
import com.mysite.sbb.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/bookmark")
@RequiredArgsConstructor
@Controller
public class BookmarkController {
	private final MemberService memberService;
	private final ManualService manualService;
	
	private final BookmarkRepository bookmarkRepository;

//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/like/{id}") // 변경된 URL
//	public String bookmarkLike(Principal principal, @PathVariable("id") Integer id) {
//		Manual manual = this.manualService.getManual(id);
//		Member member = this.memberService.getMember(principal.getName());
//        Bookmark bookmark = new Bookmark();
//        bookmark.setManual(manual);
//        bookmark.setAuthor(member);
//		return "/manual/list";
//	}
	
	
	@PostMapping("/{id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> bookmarkLike(Principal principal, @PathVariable("id") Integer id) {
	    
	    Manual manual = manualService.getManual(id);
	    Member member = memberService.getMember(principal.getName());

	    Bookmark bookmark = new Bookmark();
	    bookmark.setManual(manual);
	    bookmark.setAuthor(member);
	    bookmarkRepository.save(bookmark);

	    return ResponseEntity.ok("북마크가 추가되었습니다.");
	}

	@DeleteMapping("/{manualId}/{memberId}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<?> unbookmarkLike(Principal principal, @PathVariable("manualId") Integer manualId, @PathVariable("memberId") Integer memberId) {
	    
	    bookmarkRepository.deleteByAuthorAndManual(manualId, memberId);

	    return ResponseEntity.ok("북마크가 해제되었습니다.");
	}

	
}
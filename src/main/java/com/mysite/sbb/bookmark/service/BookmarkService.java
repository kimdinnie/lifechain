package com.mysite.sbb.bookmark.service;

import com.mysite.sbb.bookmark.controller.BookmarkController;
import com.mysite.sbb.bookmark.dto.BookmarkDto;
import com.mysite.sbb.bookmark.entity.Bookmark;
import com.mysite.sbb.manual.entity.Manual;
import com.mysite.sbb.manual.repository.ManualRepository;
import com.mysite.sbb.bookmark.repository.BookmarkRepository;
import com.mysite.sbb.member.entity.Member;
import com.mysite.sbb.member.repository.MemberRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
public class BookmarkService {
    private final ManualRepository manualRepository;
    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private static final Logger logger = LoggerFactory.getLogger(BookmarkService.class);

    public void insert(BookmarkDto bookmarkDto) throws Exception {
        Member member = memberRepository.findById(bookmarkDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not find member with id: " + bookmarkDto.getMemberId()));

        Manual manual = manualRepository.findById(bookmarkDto.getManualId())
                .orElseThrow(() -> new NotFoundException("Could not find manual with id: " + bookmarkDto.getManualId()));

        if (bookmarkRepository.findByMemberAndManual(member, manual).isPresent()) {
            throw new Exception("Bookmark already exists for member: " + bookmarkDto.getMemberId() + " and manual: " + bookmarkDto.getManualId());
        }

        Bookmark bookmark = Bookmark.builder()
                .manual(manual)
                .member(member)
                .build();

        bookmarkRepository.save(bookmark);
    }

    @Transactional
    public void delete(BookmarkDto bookmarkDto) throws Exception {
        Member member = memberRepository.findById(bookmarkDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not find member with id: " + bookmarkDto.getMemberId()));

        Manual manual = manualRepository.findById(bookmarkDto.getManualId())
                .orElseThrow(() -> new NotFoundException("Could not find manual with id: " + bookmarkDto.getManualId()));

        Bookmark bookmark = bookmarkRepository.findByMemberAndManual(member, manual)
                .orElseThrow(() -> new NotFoundException("Bookmark not found"));

        bookmarkRepository.delete(bookmark);
    }
}



/*

    public void insert(BookmarkDto bookmarkDto) throws Exception {
        Member member = memberRepository.findById(bookmarkDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + bookmarkDto.getMemberId()));

        Manual manual = manualRepository.findById(bookmarkDto.getManualId())
                .orElseThrow(() -> new NotFoundException("Could not found manual id : " + bookmarkDto.getManualId()));

        */
/*이미 북마크에 넣었다면 에러 반환*//*

        if (bookmarkRepository.findByMemberAndManual(member, manual).isPresent()) {
            throw new Exception();
        }

        Bookmark bookmark = Bookmark.builder()
                .manual(manual)
                .member(member)
                .build();

        bookmarkRepository.save(bookmark);
    }

*/

 /*   @Transactional
    public void delete(BookmarkDto bookmarkDto) throws Exception {
        Member member = memberRepository.findById(bookmarkDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + bookmarkDto.getMemberId()));

        Manual manual = manualRepository.findById(bookmarkDto.getManualId())
                .orElseThrow(() -> new NotFoundException("Could not found manual id : " + bookmarkDto.getMemberId()));

        Bookmark bookmark = (Bookmark) bookmarkRepository.findByMemberAndManual(member, manual)
                .orElseThrow(() -> new NotFoundException("Could not found bookmark id"));

        bookmarkRepository.delete(bookmark);
    }
}*/

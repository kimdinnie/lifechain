package com.mysite.sbb.admin.controller;

import com.mysite.sbb.admin.service.AccountService;
import com.mysite.sbb.member.dto.Member;
import com.mysite.sbb.member.dto.MemberDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    // 회원목록
    @GetMapping("/list")
    public String accountList(Model model) {
        List<MemberDto> memberDtoList= accountService.getMemberList();
        model.addAttribute("memberList",memberDtoList);
        return "/admin/templates/pages/account/list";
    }

    // 회원정보 보기
    @GetMapping("/{id}")
    public String accountDetail(@PathVariable("id") Long id, Model model){
        Member member=accountService.getMember(id);
        model.addAttribute("member", member);
        return "/admin/templates/pages/account/detail";
    }

    // 관리자의 회원삭제
    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable("id") @Valid Long id){
        accountService.deleteAccount(id);
        return "redirect:/admin/account/list";
    }

}

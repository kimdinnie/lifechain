package com.mysite.sbb.admin.controller;

import com.mysite.sbb.admin.service.AccountService;
import com.mysite.sbb.member.Member;
import com.mysite.sbb.member.MemberDto;

import com.mysite.sbb.member.MemberInfo;
import com.mysite.sbb.member.MemberInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    // 회원목록
    @GetMapping("/list")
    public String accountList(Model model) {
        List<MemberDto> memberDtoList = accountService.getMemberList();
        model.addAttribute("memberList", memberDtoList);
        return "/admin/templates/pages/account/list";
    }

    @GetMapping("/{id}")
    public String accountDetail(@PathVariable("id") Long id, Model model) {
        Member member = accountService.getMember(id);
        MemberInfo memberInfo = accountService.getMemberInfoByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("memberInfo", memberInfo);

        if (member != null && (memberInfo == null || member.getId().equals(memberInfo.getId()))) {
            // 회원 정보가 존재하고
            // 회원 정보 객체가 null이 아니거나, 두 객체가 일치하는 경우
            // 상세 페이지를 반환
            return "/admin/templates/pages/account/detail";
        }

        return "redirect:/admin/error"; // 오류페이지로
    }


    // 관리자의 회원삭제
    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable("id") @Valid Long id) {
        accountService.deleteAccount(id);
        return "redirect:/admin/account/list";
    }

    // 관리자의 회원수정
    @GetMapping("/update/{id}")
    public String goUpdateMemberForm(@PathVariable("id") @Valid Long id, Model model) {
        Member member = accountService.getMember(id);
        MemberInfo memberInfo = accountService.getMemberInfoByMember(member);

        model.addAttribute("member", member);
        model.addAttribute("memberInfo", memberInfo);

        if (member != null && (memberInfo == null || member.getId().equals(memberInfo.getId()))) {
            // 회원 정보가 존재하고
            // 회원 정보 객체가 null이 아니거나, 두 객체가 일치하는 경우
            // 수정페이지에 value 값을 담아서 반환
            return "/admin/templates/pages/account/setting";
        }
        return null;
    }


}

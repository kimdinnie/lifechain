package com.mysite.sbb;

import com.mysite.sbb.member.MemberRegisterForm;
import com.mysite.sbb.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;

import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    MemberService memberService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "form_errors";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }
    // PostMapping은 스프링 시큐리티가 대신 처리.

    @GetMapping("/register")
    public String register(MemberRegisterForm memberRegisterForm) {
        return "register_form";
    }

    @PostMapping("/register")
    public String register(@Valid MemberRegisterForm memberRegisterForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register_form";
        }
        if(!memberRegisterForm.getMemberPw1().equals(memberRegisterForm.getMemberPw2())) {
            bindingResult.rejectValue("memberPw2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
            return "register_form";
        }
        try {
            memberService.create(memberRegisterForm.getMemberId(), memberRegisterForm.getMemberPw1(), memberRegisterForm.getMemberNick());
        }catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 회원입니다.");
            return "register_form";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "register_form";
        }

        return "redirect:/";
    }

}

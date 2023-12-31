package com.mysite.sbb.member;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.EmailException;
import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.comment.Comment;
import com.mysite.sbb.member.mail.TempPasswordForm;
import com.mysite.sbb.question.Question;

import org.springframework.ui.Model;

import java.security.Principal;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;


@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	private static final String forgot_pass = "forgot_pass";
	
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
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	// PostMapping은 스프링 시큐리티가 대신 처리.
	
	@GetMapping("/passcheck")
	public String passcheck() {
		return "/pass_check";
	}
	
	@PostMapping("/passcheck")
	public String passcheck(@Valid PassCheckForm passCheckForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "pass_check";
		}
		if(!passCheckForm.getMemberPw1().equals(passCheckForm.getMemberPw2())) {
			bindingResult.rejectValue("memberPw2", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
			return "pass_check";
		}
	return "/member/setting";
	}
	
	@PostMapping("/pass_check")
	public String checkPassword(@RequestParam("password") String password) {
	    // 패스워드 검증 로직
	    if (passwordIsValid(password)) {
	        return "redirect:/member/setting";
	    } else {
	        // 패스워드가 유효하지 않은 경우에 대한 처리
	        return "redirect:/pass_check.html?error";
	    }
	}
	
	private boolean passwordIsValid(String password) {
		// TODO Auto-generated method stub
		return false;
	}
	


	@PreAuthorize("isAuthenticated()")
	@GetMapping("/setting")
	public String setting(Model model, Principal principal) {
		String username = principal.getName();
		member member = this.memberService.getMember(username);
		model.addAttribute("member", member);
		return "/member/member_setting";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/setting")
	public String setting(Principal principal, MemberRegisterForm memberRegisterForm, BindingResult bindingResult, Model model) {
	    if (bindingResult.hasErrors()) {
	        return "/member/member_setting";
	    }
	    
	    String username = principal.getName();
	    member member = this.memberService.getMember(username);
	    if (!member.getMemberId().equals(memberRegisterForm.getMemberId())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	    }
	    
	    this.memberService.memberModify(member, memberRegisterForm.getMemberId(), memberRegisterForm.getMemberNick());
	    return "redirect:/member/setting";
	}

    @GetMapping("/{memberNick}")
    public String userQuestionList(@PathVariable("memberNick") String memberNick, Model model) {
        member member = memberService.getMemberByMemberNick(memberNick);
        List<Question> questions = member.getQuestion();
        List<Answer> answers = member.getAnswer();
        List<Comment> comments = member.getComment();
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answers);
        model.addAttribute("comments", comments);
        model.addAttribute("member", member);

        return "/member/member_page";
    }
    

	// 이메일 인증
	@GetMapping("/tempPassword")
	String forgotpass() {
		return "forgot_pass";
	}
	
    @PostMapping("/tempPassword")
    public String sendTempPassword(@Valid TempPasswordForm tempPasswordForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
        	return "forgot_pass";
        }

        try {
            memberService.modifyPassword(tempPasswordForm.getMemberId());
        } catch (DataNotFoundException e) {
            e.printStackTrace();
            bindingResult.reject("emailNotFound", e.getMessage());
            return "forgot_pass";
        } catch (EmailException e) {
            e.printStackTrace();
            bindingResult.reject("sendEmailFail", e.getMessage());
            return "forgot_pass";
        }
        return "redirect:/";
    }

	
}
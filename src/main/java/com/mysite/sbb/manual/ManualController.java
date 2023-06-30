package com.mysite.sbb.manual;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/manual")
@RequiredArgsConstructor
@Controller
public class ManualController {
	
	private final ManualService manualService;

	
	@GetMapping("/list")
	public String list(Model model) {
        List<Manual> manualList = this.manualService.getList();
		model.addAttribute("manualList", manualList);
		return "/manual/manual_list";
	}
	
	@GetMapping("/detail")
	public String detail() {
		return "/manual/manual_detail";
	}

	
}
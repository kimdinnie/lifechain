package com.mysite.sbb.etc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EctController {
	
	@GetMapping(value = "chart")
	public String chart() {
		return "/chart";
	}

}

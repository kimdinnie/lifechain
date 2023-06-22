package com.mysite.sbb;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;

@Controller
public class MainController {
	
    @GetMapping("/")
    public String root() {
        return "index";
    }
    
}

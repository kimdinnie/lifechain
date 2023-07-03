package com.mysite.sbb.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String adminIndex() {
        return "/admin/index";
    }

    @GetMapping("/error")
    public String adminError(){
        return "/admin/templates/pages/error";
    }

}

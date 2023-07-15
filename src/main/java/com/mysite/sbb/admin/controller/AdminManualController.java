package com.mysite.sbb.admin.controller;

import com.mysite.sbb.member.entity.Manual;
import com.mysite.sbb.member.service.ManualService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("adminManualController")
@RequiredArgsConstructor
@RequestMapping("/admin/manual")
public class AdminManualController {

    private final ManualService manualService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Manual> manualList = this.manualService.getList();
        model.addAttribute("manualList", manualList);
        return "/manual/manual_list";

    }
}

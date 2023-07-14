package com.mysite.sbb.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mysite.sbb.member.entity.Manual;
import com.mysite.sbb.member.dto.ManualContent;
import com.mysite.sbb.member.service.ManualContentService;
import com.mysite.sbb.member.service.ManualService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/manual")
@RequiredArgsConstructor
@Controller
public class ManualController {
	
	private final ManualService manualService;
	private final ManualContentService manualContentService;
	
	
	@GetMapping("/list")
	public String list(Model model) {
        List<Manual> manualList = this.manualService.getList();
		model.addAttribute("manualList", manualList);
		return "/manual/manual_list";
		
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Manual manual = this.manualService.getManual(id);
		model.addAttribute("manual", manual);
		return "/manual/manual_detail";
	}
	


	@ResponseBody
	@RequestMapping(value = "/manualContent", method = RequestMethod.GET)
	public List<ManualContent> manualList() {
	    List<ManualContent> manualContentList = manualContentService.getManualContentWithManual();
	    return manualContentList;
	}
	

	 @GetMapping("/search")
	 public String search() {
		 return "/manual/manual_search";
	    }

	
//	public ManualContent getMContent(Integer id) {
//	    String sql = "SELECT m.id, m.title, c.subject, c.content " +
//	                "FROM manual m" +
//	                "LEFT JOIN manual_content c " +
//	                "ON m.id = c.m_id " +
//	                "WHERE m.id = ? " +
//	                "ORDER BY c.id ASC " +
//	                "LIMIT 1;";
//	    List<ManualContent> result = JdbcTemplate.query(sql, new Object[]{id}, new MContentRowMapper());
//	    return result.isEmpty() ? null : result.get(0);
//	}

	

	
}
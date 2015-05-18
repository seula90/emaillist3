package com.sds.icto.emaillist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sds.icto.emaillist.dao.EmailListdao;
import com.sds.icto.emaillist.vo.EmailListvo;

@Controller
public class EmaillistController {
	
	@Autowired
	EmailListdao emailListDao;
	
	@RequestMapping("/index")
	public String index(Model model){
		List <EmailListvo> list = emailListDao.fetchList();
		model.addAttribute("list",list);
		return "/views/show_emaillist.jsp";
	}
	
	@RequestMapping("/form")
	public String form(){
		return "/views/form_emaillist.jsp";
	}
	
	
	@RequestMapping(value="/insert",method= RequestMethod.POST)
		public String insert(
			@RequestParam("fn") String firstName,
			@RequestParam("ln") String lastName,
			@RequestParam String email){
		
		EmailListvo vo = new EmailListvo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);
		
		emailListDao.insert(vo);
		return "redirect:/index";
	}
}

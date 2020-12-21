package com.tasinirdepo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TasinirDepoController {

	
	
	@RequestMapping("/")
	public String index() {
		ModelAndView mav=new ModelAndView();
				
		mav.setViewName("index.html");
		return "redirect:/index.html";
	}
	
	@RequestMapping("/index")
	public ModelAndView get() {
		ModelAndView mov=new ModelAndView();
		mov.setViewName("index");
		return mov;
		
	}
}

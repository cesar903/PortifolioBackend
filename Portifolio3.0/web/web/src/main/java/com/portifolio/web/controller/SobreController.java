package com.portifolio.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SobreController {
	 
	@RequestMapping(value="/sobre", method = RequestMethod.GET)
	public ModelAndView abrirSobre(){
		ModelAndView mv = new ModelAndView("sobre");
		return mv;
		
	}
}

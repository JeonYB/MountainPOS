package com.san.mpos.mgr.tb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/mgr/tb/")
public class MgrTbController {
	/** The logger. */
	Logger logger = LoggerFactory.getLogger(MgrTbController.class);
	
	public MgrTbController(){
		System.out.println("CREATE!");
	}
	
	@RequestMapping("page")
	public ModelAndView page(){
		System.out.println("COME!");
		ModelAndView mav = new ModelAndView("/mgr/tb/page");
		return mav;
	}
	
}

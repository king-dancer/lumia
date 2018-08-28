package com.spring.lemon.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LonginWeb {
	
	@GetMapping(value="/login")
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}
}

package com.spring.btable.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.btable.dto.Hero;
import com.spring.btable.service.HeroService;

@Controller
public class HeroController {
	@Autowired
	private HeroService hs;
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/heroCount")
	public Integer findHeroCount(){
		return hs.findHeroCount();
	}
	
	@ResponseBody 
	@RequestMapping("/hero")
	public Map<String,Object> findHero(Integer offset,Integer pageSize){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", hs.findHero(offset, pageSize));
		map.put("total", hs.findHeroCount());
		return map;
	}
}

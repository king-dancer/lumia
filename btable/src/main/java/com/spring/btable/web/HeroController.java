package com.spring.btable.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.btable.dto.Hero;
import com.spring.btable.service.HeroService;

@Controller
public class HeroController {
	private Logger logger = LoggerFactory.getLogger(HeroController.class);
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
		logger.info("/hero接收到参数，当前页：{}-每页显示数量：{}",offset,pageSize);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", hs.findHero(offset, pageSize));
		map.put("total", hs.findHeroCount());
		return map;
	}
	@ResponseBody
	@RequestMapping("/pageHero")
	public Map<String, Object> queryPageHero(Hero hero,Integer offset,Integer pageSize){
		Map<String, Object> map = new HashMap<>();
		logger.info(new Date().toString());
		int count = hs.pageCount(hero);
		map.put("total", count);
		map.put("rows", hs.pageHero(hero, offset, pageSize));
		logger.info(new Date().toString());
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/updateHero")
	public Map<String,Object> updateHero(Hero hero){
		Map<String,Object> map = new HashMap<>();
		hs.updateHero(hero);
		map.put("status", "SUCCESS");
		return map;
	}
}

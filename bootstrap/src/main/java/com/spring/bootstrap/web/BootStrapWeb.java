package com.spring.bootstrap.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BootStrapWeb {
	
	@RequestMapping("/")
	public String bootStrap(){
		return "bootstrap";
	}
	@RequestMapping("/go")
	public String go(String name,Model model){
		model.addAttribute("name", name);
		return "go";
	}
	@RequestMapping("/submit")
	@ResponseBody
	public Map<String, Object> submit(String name){
		Map<String, Object> map = new HashMap<>();
		Map<String, String> gaylun = new HashMap<>();
		List<Map<String,String>> list = new ArrayList<>();
		gaylun.put("id", "001");
		gaylun.put("name", "盖伦");
		gaylun.put("nickname", "德玛西亚之力");
		list.add(gaylun);
		Map<String, String> dles = new HashMap<>();
		dles.put("id", "002");
		dles.put("name", "德莱厄斯");
		dles.put("nickname", "诺克萨斯之手");
		list.add(dles);
		map.put("rows", list);
		map.put("total", 2);
		return map;
	}
}

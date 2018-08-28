package com.spring.btable.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
	@RequestMapping("/navbar")
	public String navbar(){
		return "navbar";
	}
	@RequestMapping("/personInfo")
	public String personInfo(){
		return "personInfo";
	}
	@ResponseBody
	@RequestMapping("/heroCount")
	public Integer findHeroCount(){
		return hs.findHeroCount();
	}
	/**
	 * 自定义sql分页controller
	 * @param offset   当前页    注意：bootstrap table传的此参数是进过计算的（pageNum-1）*pageSize
	 * @param pageSize 一页显示数量
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("/hero")
	public Map<String,Object> findHero(Integer offset,Integer pageSize){
		logger.info("/hero接收到参数，当前页：{}-每页显示数量：{}",offset,pageSize);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", hs.findHero(offset, pageSize));
		map.put("total", hs.findHeroCount());
		return map;
	}
	/**
	 * JPA 分页并模糊搜索
	 * @param hero
	 * @param offset 	当前页    注意：bootstrap table传的此参数是进过计算的（pageNum-1）*pageSize
	 * @param pageSize	一页显示数量
	 * @return
	 */
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
	
	/**
	 * 获取json数据方式之一
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/test",produces="application/json;charset=UTF-8")
	public Map<String,String> test(@RequestBody JSONObject json){
		Map<String, String> map = new HashMap<>();
		map.put("res", (String)json.get("name"));
		return map;
	}
	@ResponseBody
	@RequestMapping(value="/json")
	public String json(HttpServletRequest request){
		JSONObject json = getJsonString(request);
		String name = (String)json.get("name");
		JSONObject result = new JSONObject();
		result.put("res", name);
		return result.toJSONString();
	}
	public JSONObject getJsonString(HttpServletRequest request){
		try{
			ServletInputStream inputStream = request.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer sb = new StringBuffer();
			String lines;
			while((lines=br.readLine())!=null){
				sb.append(lines);
			}
			return JSON.parseObject(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(value="/httpClientJson",method=RequestMethod.POST)
	public String httpClientJson(@RequestBody JSONObject json){
		JSONObject result = new JSONObject();
		result.put("result", json.getString("name"));
		return result.toJSONString();
	}
}

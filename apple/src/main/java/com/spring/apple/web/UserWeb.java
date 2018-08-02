package com.spring.apple.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.spring.apple.dto.Area;
import com.spring.apple.dto.City;
import com.spring.apple.dto.Msg;
import com.spring.apple.dto.Province;
import com.spring.apple.dto.SysUser;
import com.spring.apple.service.AppleUserService;

@Controller
public class UserWeb {
	private Logger logger = LoggerFactory.getLogger(UserWeb.class);
	@Autowired
	AppleUserService aus;
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	@ResponseBody
	@PostMapping("/getProvince")
	public String getProvince(){
		List<Province> provinces = aus.getProvince();
		logger.info("进入{}请求", "/getProvince");
		return JSON.toJSONString(provinces);
	}
	
	@ResponseBody
	@PostMapping("/getCity")
	public String getCity(String provinceCode){
		logger.info("请求/getCity接收到参数,provinceCode：{}", provinceCode);
		List<City> citys = aus.getCityByProvinceCode(provinceCode);
		logger.info("请求/getCity返回：{}",JSON.toJSON(citys));
		return JSON.toJSONString(citys);
	}
	
	@ResponseBody
	@PostMapping("/getArea")
	public String getArea(String cityCode){
		logger.info("请求/getArea接收到参数,cityCode：{}", cityCode);
		List<Area> areas = aus.getAreaByCityCode(cityCode);
		logger.info("请求/getArea返回：{}",JSON.toJSON(areas));
		return JSON.toJSONString(areas);
	}
	
	@RequestMapping("/")
	public String index(Model model,Authentication authentication){
		SysUser u = (SysUser)authentication.getPrincipal();
		Msg msg = new Msg(u.getUsername(), JSON.toJSONString(u.getRoles()), "只对管理员显示");
		model.addAttribute("msg", msg);
		return "index123";
	}
	
	@RequestMapping("/index")
	public String index1(Model model,Authentication authentication){
		SysUser u = (SysUser)authentication.getPrincipal();
		Msg msg = new Msg(u.getUsername(), JSON.toJSONString(u.getRoles()), "只对管理员显示");
		model.addAttribute("msg", msg);
		System.out.println("/index");
		return "index";
	}
	
	@RequestMapping("/upload")
	@ResponseBody
	public Map<String,String> upload(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws Exception{
		String filename = file.getOriginalFilename();
		String filePath = request.getSession().getServletContext().getRealPath("upload/");
		filePath = request.getSession().getServletContext().getRealPath("/");
		logger.info(filePath);
		filePath = "/app/apple/images/";
		File sFile = new File(filePath);
		if(!sFile.exists()){
			sFile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(filePath+filename);
		fos.write(file.getBytes());
		fos.flush();
		fos.close();
		Map<String,String> map = new HashMap<>();
		map.put("msg", "上传成功");
		return map;
	}
	@ResponseBody
	@RequestMapping("/jsonToObj")
	public String testJsonToObj(SysUser sysUser){
		System.out.println(sysUser.toString());
		return sysUser.toString();
	}
}

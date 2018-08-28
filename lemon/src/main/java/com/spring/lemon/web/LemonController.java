package com.spring.lemon.web;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spring.lemon.dto.Area;
import com.spring.lemon.dto.City;
import com.spring.lemon.dto.Province;
import com.spring.lemon.service.LemonService;

@Controller
public class LemonController {
	@Autowired
	private LemonService ls;
	
	@ResponseBody
	@PostMapping(value="/getProvince")
	public String getProvince(){
		List<Province> provinceList = ls.getProvince();
		return JSON.toJSONString(provinceList);
	}
	
	
	@ResponseBody
	@GetMapping(value="/getCityByProvinceCode")
	public String getCityByProvinceCode(@RequestParam("provinceCode") String provinceCode){
		List<City> cityList = ls.getCity(provinceCode);
		return JSON.toJSONString(cityList);
	}
	
	
	@ResponseBody
	@GetMapping(value="/getAreaByCityCode/{cityCode}")
	public String getAreaByCityCode(@PathVariable("cityCode") String cityCode){
		List<Area> areaList = ls.getArea(cityCode);
		return JSON.toJSONString(areaList);
	}
	
	@ResponseBody
	@RequestMapping("/jsonToObj")
	public String testJsonToObj(Province province){
		System.out.println(province.toString());
		return province.toString();
	}
	
	@ResponseBody
	@RequestMapping("/jsonToObj1")
	public String testJsonToObj(HttpServletRequest request) throws IOException{
		int totalbytes = request.getContentLength();
        byte[] dataOrigin = new byte[totalbytes];
        // 得到请求消息的数据输入流
        DataInputStream in = new DataInputStream(request.getInputStream());
        in.readFully(dataOrigin); // 根据长度，将消息实体的内容读入字节数组dataOrigin中
        in.close(); // 关闭数据流
        String reqcontent = new String(dataOrigin); // 从字节数组中得到表示实体的字符串
        JSONObject json = JSON.parseObject(reqcontent);
		System.out.println(json.toString());
		return json.toString();
	}
}

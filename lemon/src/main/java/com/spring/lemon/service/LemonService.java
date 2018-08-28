package com.spring.lemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.lemon.dto.Area;
import com.spring.lemon.dto.City;
import com.spring.lemon.dto.Province;
import com.spring.lemon.repository.AreaRepository;
import com.spring.lemon.repository.CityRespository;
import com.spring.lemon.repository.ProvinceRepository;

@Service
public class LemonService {
	@Autowired
	private ProvinceRepository pr;
	@Autowired
	private CityRespository cr;
	@Autowired
	private AreaRepository ar;
	
	public List<Province> getProvince(){
		List<Province> provinceList = pr.findAll();
		return provinceList;
	}
	
	public List<City> getCity(String provinceCode){
		List<City> cityList = cr.findByProvinceCode(provinceCode);
		return cityList;
	}
	
	public List<Area> getArea(String cityCode){
		List<Area> areaList = ar.findByCityCode(cityCode);
		return areaList;
	}
}

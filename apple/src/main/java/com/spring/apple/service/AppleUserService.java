package com.spring.apple.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.apple.dto.Area;
import com.spring.apple.dto.City;
import com.spring.apple.dto.Province;
import com.spring.apple.dto.SysUser;
import com.spring.apple.repository.AreaRepository;
import com.spring.apple.repository.CityRepository;
import com.spring.apple.repository.ProvinceRepository;
import com.spring.apple.repository.SysUserRepository;

@Component
public class AppleUserService implements UserDetailsService{
	@Autowired
	SysUserRepository sur;
	@Autowired
	ProvinceRepository pr;
	@Autowired
	CityRepository cr;
	@Autowired
	AreaRepository ar;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = sur.findByUsername(username);
		if(user==null){
			throw new UsernameNotFoundException("用户名不存在！");
		}
		System.out.println("输入的用户："+username);
		System.out.println(user.toString());
		return user;
	}
	public List<Province> getProvince(){
		return pr.findAll();
	}
	
	public List<City> getCityByProvinceCode(String provinceCode){
		return cr.findByProvinceCode(provinceCode);
	}
	
	public List<Area> getAreaByCityCode(String cityCode){
		return ar.findByCityCode(cityCode);
	}
}

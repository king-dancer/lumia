package com.spring.btable.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.spring.btable.dto.Hero;
import com.spring.btable.repository.HeroRepository;

@Service
public class HeroService {
	@Autowired
	private HeroRepository hr;
	public Integer findHeroCount(){
		return hr.findCount();
	}
	public List<Hero> findHero(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize){
//		pageNum = (pageNum-1)*pageSize;
		return hr.queryHero(pageNum, pageSize);
	}
}

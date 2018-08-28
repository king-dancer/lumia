package com.spring.btable.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
		return hr.queryHero(pageNum, pageSize);
	}
	
	/**
	 * 模糊搜索并分页
	 * @param hero
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<Hero> pageHero(Hero hero,int pageNum,int pageSize){
		Specification<Hero> specification = getSpecification(hero);
		//new PageRequest(pageNum,pageSize)过时，使用PageRequest.of(pageNum,pageSize)代替，此时pageNum是从0开始代表第1页，1代表第2页。。。。
		pageNum = pageNum / pageSize;
		Page<Hero> pageHero = hr.findAll(specification, PageRequest.of(pageNum, pageSize));
		return pageHero.getContent();
	}
	
	/**
	 * 加上模糊搜索总条数
	 * @return
	 */
	public int pageCount(Hero hero){
		Specification<Hero> specification = getSpecification(hero);
		return hr.findAll(specification).size();
	}
	/**
	 * 获取Specification对象
	 * @param hero
	 * @return
	 */
	public Specification<Hero> getSpecification(Hero hero){
		return new Specification<Hero>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Hero> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(hero != null){
					if(!StringUtils.isEmpty(hero.getSex())){
						predicate.getExpressions().add(cb.equal(root.get("sex"),hero.getSex()));
					}
					if(!StringUtils.isEmpty(hero.getId())){
						predicate.getExpressions().add(cb.equal(root.get("id"), hero.getId()));
					}
					if(!StringUtils.isEmpty(hero.getName())){
						predicate.getExpressions().add(cb.like(root.get("name"),"%"+hero.getName()+"%"));
					}
				}
				return predicate;
			}
		};
	}
	
	public void updateHero(Hero hero){
		hr.save(hero);
	}
}

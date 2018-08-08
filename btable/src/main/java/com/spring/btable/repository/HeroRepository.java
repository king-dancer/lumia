package com.spring.btable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.btable.dto.Hero;

public interface HeroRepository extends JpaRepository<Hero, Long> {
	@Query(value="select count(1) from hero",nativeQuery=true)
	Integer findCount();
	@Query(value="select id,name,nickname,sex from hero  order by id limit :pageNum,:pageSize",nativeQuery=true)
	List<Hero> queryHero(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);
}

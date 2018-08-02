package com.spring.apple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.apple.dto.Area;

public interface AreaRepository extends JpaRepository<Area, Long>{
	List<Area> findByCityCode(String cityCode);
}

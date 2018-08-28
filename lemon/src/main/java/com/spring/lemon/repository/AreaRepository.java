package com.spring.lemon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.lemon.dto.Area;

public interface AreaRepository extends JpaRepository<Area, Long>{
	List<Area> findByCityCode(String cityCode);
}

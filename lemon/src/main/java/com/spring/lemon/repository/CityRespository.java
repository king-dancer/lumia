package com.spring.lemon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.lemon.dto.City;

public interface CityRespository extends JpaRepository<City, Long> {
	List<City> findByProvinceCode(String provinceCode);
}

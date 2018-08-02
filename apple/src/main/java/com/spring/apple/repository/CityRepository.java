package com.spring.apple.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.apple.dto.City;

public interface CityRepository extends JpaRepository<City, Long> {
	List<City> findByProvinceCode(String provinceCode);
}

package com.spring.lemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.lemon.dto.Province;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
}

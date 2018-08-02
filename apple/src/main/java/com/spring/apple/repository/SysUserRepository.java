package com.spring.apple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.apple.dto.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Long>{
	SysUser findByUsername(String username);
}

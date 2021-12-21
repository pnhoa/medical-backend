package com.nhuhoa.medicalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhuhoa.medicalmanagement.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByCode(String code);

}

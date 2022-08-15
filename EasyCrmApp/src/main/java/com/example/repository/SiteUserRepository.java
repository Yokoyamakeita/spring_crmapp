package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.SiteUser;

public interface SiteUserRepository extends JpaRepository<SiteUser,Long>{

	SiteUser findByUsername(String username);
	boolean existsByUsername(String username);
}

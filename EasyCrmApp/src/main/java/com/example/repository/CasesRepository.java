package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Cases;

public interface CasesRepository extends JpaRepository<Cases,Long>{

}

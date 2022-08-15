package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Cases;
import com.example.repository.CasesRepository;

@Service
public class CaseService {

	@Autowired
	private CasesRepository casesRepository;
	
	
	public List<Cases> findAll(){
		return casesRepository.findAll();
	}
	
	public Cases findOne(Long id) {
		return casesRepository.findById(id).get();
	}
	
	public Cases save(Cases cases) {
		return casesRepository.save(cases);
	}
}

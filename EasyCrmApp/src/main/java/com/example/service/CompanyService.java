package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Company;
import com.example.repository.CompanyRepository;


@Service
public class CompanyService {
	
	
	
	@Autowired
	private CompanyRepository companyRepository;
	

	
	public List<Company> findAll(){
		
		return companyRepository.findAll();
	}
	
	public Company findOne(Long id) {
		return companyRepository.findById(id).get();
	}
	
	public Company save(Company company) {
		return companyRepository.save(company);
	}
	
	
	public void delete(Long id) {
		companyRepository.deleteById(id);
	}
	
	
	
}

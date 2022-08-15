package com.example.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.model.Department;
import com.example.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class dataload implements CommandLineRunner{

	private final DepartmentRepository departmentRepository;
	
	@Override
	public void run(String...args) throws Exception{
		
		
		if(departmentRepository.count() == 0) {
			
			Department manager = new Department();
			
			manager.setName("経営");
			
			departmentRepository.save(manager);
			
			Department supporter = new Department();
			
			supporter.setName("サポート");
			
			departmentRepository.save(supporter);
			
			Department develop = new Department();
			
			develop.setName("開発");
			
			departmentRepository.save(develop);
			
			Department sales = new Department();
			
			sales.setName("営業");
			
			departmentRepository.save(sales);
		}
	}
}

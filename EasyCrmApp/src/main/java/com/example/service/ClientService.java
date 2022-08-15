package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Clients;
import com.example.repository.ClientsRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientsRepository ClientsRepository;
	
	public List<Clients> findAll(){
		return ClientsRepository.findAll();
	}
	
	public Clients findOne(Long id) {
		return ClientsRepository.findById(id).get();
	}
	
	public Clients save(Clients clients) {
		return ClientsRepository.save(clients);
	}
	
	
	public void delete(Long id) {
		ClientsRepository.deleteById(id);
	}
}

package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Clients;

public interface ClientsRepository extends JpaRepository<Clients,Long>{

}

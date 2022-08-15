package com.example.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Department {
	
	
//	部署情報のやりとり
	
	//DBのidentity列を使用して、 キーを自動採番します。 	
	@Id
	@GeneratedValue(strategy = GenerationType. IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max=40)
	private String name;

//	リレーション
	@OneToMany(mappedBy = "department")
	private List<SiteUser> siteuser;
}

	

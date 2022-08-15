package com.example.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Cases;
import com.example.model.Clients;
import com.example.model.Company;
import com.example.model.SiteUser;
import com.example.repository.CasesRepository;
import com.example.repository.ClientsRepository;
import com.example.repository.SiteUserRepository;
import com.example.service.CaseService;
import com.example.service.ClientService;
import com.example.service.CompanyService;
import com.example.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SecurityController {

	private final SiteUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final ClientsRepository clientRepository;
	private final CasesRepository casesRepository;
	
	

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CaseService caseService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String showList(Authentication loginUser, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());

		model.addAttribute("users", userRepository.findAll());

		return "list";
	}

	@GetMapping("/admin/list")
	public String AdminList(Authentication loginUser, Model model) {

		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());

		model.addAttribute("users", userRepository.findAll());

		return "list";
	}

	@GetMapping("/register")
	public String register(@ModelAttribute("user") SiteUser user) {
		return "register";
	}

	@PostMapping("/register")
	public String register(@Validated @ModelAttribute("user") SiteUser user, BindingResult result) {

		if (result.hasErrors()) {
			return "register";
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (user.isAdmin()) {
			user.setRole(Role.ADMIN.name());
		} else {
			user.setRole(Role.USER.name());
		}
		userRepository.save(user);

		return "redirect:/login?register";
	}
	
	
	
	//取引先企業
	
	

	@GetMapping("/company/list")
	public String getcompany(Authentication loginUser, Model model,Pageable pageable) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());
		

		model.addAttribute("company",companyService.findAll());
		
		

		return "company";

	}

	@GetMapping("/company/add")
	public String addcompany(Authentication loginUser, @ModelAttribute("company") Company company, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());

		return "addcompany";

	}

	@PostMapping("/company/add")
	public String postcompany(Authentication loginUser, @Validated @ModelAttribute("company") Company company,
			BindingResult result, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());

		if (result.hasErrors()) {
			return "addcompany";
		}
		companyService.save(company);

		return "redirect:/company/list";

	}

	@GetMapping("/company/edit/{id}")
	public String editcompany(Authentication loginUser, @PathVariable Long id, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());

		Company company = companyService.findOne(id);

		model.addAttribute("company", company);

		return "editcompany";
	}

	@PostMapping("/company/editprocess/{id}")
	public String companyeditprocass(@PathVariable Long id, @Validated @ModelAttribute Company company,
			BindingResult result) {

		if (result.hasErrors()) {
			return "editcompany";
		}

		company.setId(id);
		companyService.save(company);

		return "redirect:/company/list";
	}

	@GetMapping("company/delete/{id}")
	public String editcompany(@PathVariable Long id) {
		companyService.delete(id);

		return "redirect:/company/list";
	}

	
	
	
	
	//取引先様
	
	
	
	
	@GetMapping("/company/user")
	public String getcompanyuser(Authentication loginUser, Model model,Pageable pageable) {

		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());

		model.addAttribute("client", clientService.findAll());

		return "client";

	}
	

	@GetMapping("/company/user/add")
	public String getclient(Authentication loginUser, @ModelAttribute("client") Clients client, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());

		return "addclient";
	}

	@PostMapping("/company/user/add")
	public String postclient(Authentication loginUser, @Validated @ModelAttribute("client") Clients client,
			BindingResult result, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());

		if (result.hasErrors()) {
			return "addclient";
		}

		clientService.save(client);

		return "redirect:/company/user";
	}
	
	
	@GetMapping("/company/user/edit/{id}")
	public String geteditclient(Authentication loginUser,@PathVariable Long id, @ModelAttribute("client") Clients client, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());
		
		Clients clients = clientService.findOne(id);
		
		model.addAttribute("client", clients);
		
		return "editclient";
	}
	
	@PostMapping("/company/user/edit/{id}")
	public String posteditclient(@PathVariable Long id,@Validated @ModelAttribute("client") Clients client,BindingResult result) {
		
		if(result.hasErrors()) {
			return "editclient";
		}
		
		client.setId(id);
		clientService.save(client);
		
		return "redirect:/company/user";
	}
	
	@GetMapping("/company/user/delete/{id}")
	public String deleteclient(@PathVariable Long id) {
		clientService.delete(id);
		
		return "redirect:/company/user";
		
	}
	
	
	
	
	//案件
	
	

	@GetMapping("/case")
	public String getcases(Authentication loginUser, Model model) {

		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());

		model.addAttribute("cases", caseService.findAll());

		return "cases";
	}

	@GetMapping("/case/add")
	public String addcase(Authentication loginUser, @ModelAttribute("case") Cases cases, Model model) {
		model.addAttribute("username", loginUser.getName());
		model.addAttribute("role", loginUser.getAuthorities());

		return "addcase";
	}

	@PostMapping("/case/add")
	public String postcase(@Validated @ModelAttribute("case") Cases cases, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "addcase";
		}

		caseService.save(cases);

		return "redirect:/case";
	}
	
	
	
	@GetMapping("/case/edit/{id}")
	public String geteditcase(@PathVariable Long id) {
		
		Cases cases = caseService.findOne(id);
		
		cases.setResult(true);
		
		caseService.save(cases);
		
		return "redirect:/case";
	}

}

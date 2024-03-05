package com.hcl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.model.SBIBody;
import com.hcl.model.SBIHeader;
import com.hcl.model.SbiInput;
import com.hcl.service.SbiService;

@RestController
public class EmpController {

	@Autowired
	SbiService sbiService;

	@Value("${myown.flag}")
	Boolean flag;

	@GetMapping("/getEmpDetails")
	public List<SBIBody> getEmpDetails() {
		return sbiService.getDetails();
	}

	@GetMapping("/getEmpDetails/{id}")
	public Optional<SBIBody> getDetaildByid(@PathVariable Long id) {
		return sbiService.getDetailsByid(id);
	}

	@PostMapping("/saveEmpDetails")
	public SBIBody saveEmpDetails(@RequestBody SbiInput sbiInput) {

		SBIBody sbibody = sbiInput.getBody();
		SBIHeader heder = sbiInput.getHeader();
		SBIHeader role = sbiService.getRole(heder);

		if (role.getRole().equalsIgnoreCase("admin")) {

			return sbiService.saveEmpDetails(sbibody);

		} else {

			System.out.println("You are not an Admin");
			return null;
		}

	}

	@DeleteMapping("/deleteBuid/{id}")
	public String deleteEmpDetailsByid(@RequestBody SbiInput sbiInput, @PathVariable Long id) {

		SBIHeader heder = sbiInput.getHeader();
		SBIHeader role = sbiService.getRole(heder);

		if (role.getRole().equalsIgnoreCase("admin")) {

			sbiService.deleteById(heder, id);

			return id + " Deleted Successfully";

		} else {

			return "You are not an Admin";
		}

	}

	@PutMapping("/updateDetails/{id}")
	public String updateDetails(@RequestBody SbiInput sbiInput, @PathVariable Long id) {

		SBIHeader heder = sbiInput.getHeader();
		SBIHeader role = sbiService.getRole(heder);

		if (role.getRole().equalsIgnoreCase("admin")) {

			sbiService.updateDetails(sbiInput.getBody(), id);

			return id + " Update Successfully";

		} else {

			return "You are not an Admin";
		}

	}

	
	@PostMapping("/authentication")
	public String generateToken(@RequestBody   SBIHeader header) {
		
		return sbiService.generateToken(header.getUsername());
	}
	
	
	
	
	
	
	
	
	
	
}

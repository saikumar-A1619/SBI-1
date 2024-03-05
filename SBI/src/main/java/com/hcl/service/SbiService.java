package com.hcl.service;



import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.model.SBIBody;
import com.hcl.model.SBIHeader;
import com.hcl.repo.SBIRepository;
import com.hcl.repo.SbiRoles;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SbiService {

	
	@Autowired
	SBIRepository sBIRepository;
	@Autowired
	SbiRoles sbiRoles;

	public SBIBody saveEmpDetails(SBIBody sbibody) {

		return sBIRepository.save(sbibody);

	}

	public SBIHeader getRole(SBIHeader heder) {

		java.util.Optional<SBIHeader> op = sbiRoles.findbyUsernameandPwd(heder.getUsername(), heder.getPassword());
		SBIHeader sbheader = op.get();

		return sbheader;
	}

	
	public String deleteById(SBIHeader heder, Long id) {

		Optional<SBIBody> sbibody = sBIRepository.findById(id);

		if (sbibody.isPresent()) {

			sBIRepository.deleteById(id);

			return id + " deleted";
			
		} else {
			
			return id + "Not found";
		}
	}

	public List<SBIBody> getDetails() {
		return sBIRepository.findAll();

	}
	
	
	public Optional<SBIBody> getDetailsByid(Long id) {
		return sBIRepository.findById(id);
	}
	
	public SBIBody updateDetails(SBIBody sbibody,Long id) {
		
		Optional<SBIBody> sbibodys = sBIRepository.findById(id);
		
		if(sbibodys.isPresent()) {
			sbibodys.get().setName(sbibody.getName());
			sbibodys.get().setAge(sbibody.getAge());
			sbibodys.get().setAddress(sbibody.getAddress());
			
			return sBIRepository.save(sbibodys.get());
		} else {
			return null;
		}
		
	}
	
	
	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*60))
				.signWith( getSecKey(),SignatureAlgorithm.HS256).compact();
				
	}
	
	private Key getSecKey() {
		byte[] keybytes = Decoders.BASE64.decode("3273357638792F423F4528482B4D6251655368566D597133743677397A244326");
		return Keys.hmacShaKeyFor(keybytes);
	}
	 

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

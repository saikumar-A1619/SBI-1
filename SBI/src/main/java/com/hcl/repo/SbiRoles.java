package com.hcl.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hcl.model.SBIHeader;

public interface SbiRoles  extends JpaRepository<SBIHeader, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM roles where username=:username and password=:password")
	Optional<SBIHeader> findbyUsernameandPwd(@Param("username") String username,@Param("password") String password);

}

package com.hcl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.hcl.service.JwtService")
public class SbiAppication {

	public static void main(String[] args) {
		SpringApplication.run(SbiAppication.class, args);
	}
}


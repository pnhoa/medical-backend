package com.nhuhoa.medicalmanagement.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	
	@GetMapping("/")
	public String testPage() {
		
		return "Hello";
	}

}

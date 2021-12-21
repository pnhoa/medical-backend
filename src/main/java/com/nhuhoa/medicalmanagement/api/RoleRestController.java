package com.nhuhoa.medicalmanagement.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhuhoa.medicalmanagement.entity.Role;
import com.nhuhoa.medicalmanagement.service.IRoleService;

@RestController
@RequestMapping("/api/roles")
@PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
public class RoleRestController {
	
	@Autowired
	private IRoleService roleService;
	
	@GetMapping("")
	public ResponseEntity<List<Role>> getAll(){
		
		List<Role> roles = roleService.findAll();
		
		return new ResponseEntity<>(roles, HttpStatus.OK);
	}
	

}

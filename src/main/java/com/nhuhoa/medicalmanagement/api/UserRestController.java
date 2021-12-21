package com.nhuhoa.medicalmanagement.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhuhoa.medicalmanagement.dto.MessageResponse;
import com.nhuhoa.medicalmanagement.dto.UserDto;
import com.nhuhoa.medicalmanagement.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDto>> findAll(){
		
		List<UserDto> theUsers = userService.findAll();
		
		return new ResponseEntity<>(theUsers, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
	public ResponseEntity<UserDto> findById(@PathVariable("id") Long theId){
		
		UserDto theUser = userService.findById(theId);
		
		return new ResponseEntity<UserDto>(theUser, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDto>> searchUsers(@RequestParam(name="key", required = false) String key){
		
		List<UserDto> theUserDtos = userService.search(key);
		
		return new ResponseEntity<>(theUserDtos, HttpStatus.OK);
		
	}
	
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> updateUser(@PathVariable("id") Long theId,
											@Valid @RequestBody UserDto theUserDto){
		
		MessageResponse messageResponse = userService.updateUser(theId, theUserDto);
		
		return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
	}
	
	@PostMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long theId){
		
		userService.deleteUser(theId);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

package com.nhuhoa.medicalmanagement.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhuhoa.medicalmanagement.common.JwtUtils;
import com.nhuhoa.medicalmanagement.dto.JwtResponse;
import com.nhuhoa.medicalmanagement.dto.LoginDto;
import com.nhuhoa.medicalmanagement.dto.MessageResponse;
import com.nhuhoa.medicalmanagement.dto.UserDto;
import com.nhuhoa.medicalmanagement.entity.UserDetailsImpl;
import com.nhuhoa.medicalmanagement.service.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IUserService userService;
	
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginDto loginDto){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUserName(),
						loginDto.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(new JwtResponse(jwt,
												userDetails.getId(),
												userDetails.getUsername(),
												userDetails.getEmail(),
												roles));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Validated @RequestBody UserDto userDto){
	
		if(userService.existsByUserName(userDto.getUserName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already use."));
								
		}
		
		if(userService.existsByEmail(userDto.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already use."));
			
		}
		

		userService.createUser(userDto);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
		
	}

}

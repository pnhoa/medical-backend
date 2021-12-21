package com.nhuhoa.medicalmanagement.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nhuhoa.medicalmanagement.dto.MessageResponse;
import com.nhuhoa.medicalmanagement.dto.UserDto;
import com.nhuhoa.medicalmanagement.entity.User;

public interface IUserService extends  UserDetailsService {
	
	public List<UserDto> findAll();
	
	public UserDto findById(Long theId);
	
	public UserDto findByUserName(String userName);

	public User findByEmail(String email);
	
	public MessageResponse createUser(UserDto theUser);
	
	public MessageResponse updateUser(Long theId, UserDto theUser);
	
	public void deleteUser(Long theId);
	
	public Boolean existsByEmail(String email);
	
	public Boolean existsByUserName(String username);

	public List<UserDto> search(String key);
}

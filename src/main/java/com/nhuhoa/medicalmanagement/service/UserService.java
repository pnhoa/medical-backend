package com.nhuhoa.medicalmanagement.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.medicalmanagement.dto.MessageResponse;
import com.nhuhoa.medicalmanagement.dto.UserDto;
import com.nhuhoa.medicalmanagement.entity.Role;
import com.nhuhoa.medicalmanagement.entity.User;
import com.nhuhoa.medicalmanagement.entity.UserDetailsImpl;
import com.nhuhoa.medicalmanagement.exception.ResourceNotFoundException;
import com.nhuhoa.medicalmanagement.repository.RoleRepository;
import com.nhuhoa.medicalmanagement.repository.UserRepository;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	@Qualifier("passwordEncoder")
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(username);
		 
        if ( (!user.isPresent()) || user.get().getEnabled() == 0) {
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
 
        return UserDetailsImpl.build(user.get());
	}
	
	@Override
	public List<UserDto> findAll() {
		
		List<User> theUsers = userRepository.findAll();
		
		List<UserDto> theUsersDto = new ArrayList<UserDto>();
		
		for(User theUser :theUsers) {
			if(theUser.getEnabled() == 1) {
				UserDto theUserDto = new UserDto();
				theUserDto.setId(theUser.getId());
				theUserDto.setUserName(theUser.getUserName());
				theUserDto.setFullName(theUser.getFullName());
				theUserDto.setEmail(theUser.getEmail());
				theUserDto.setPhone(theUser.getPhone());
				if(theUser.getRoles().size() == 2) {
					theUserDto.setRoleCode("ROLE_ADMIN");
				} else {
					theUserDto.setRoleCode("ROLE_EMPLOYEE");
				}
				theUsersDto.add(theUserDto);
			}
		}
		
		return theUsersDto;
	}

	@Override
	public UserDto findById(Long theId) throws ResourceNotFoundException {
		
		Optional<User> theUser = userRepository.findById(theId);
		
		if(!theUser.isPresent()) {
			throw new ResourceNotFoundException("Not found user with ID=" + theId);
		} else {
			if(theUser.get().getEnabled() == 1) {
				UserDto theUserDto = new UserDto();
				theUserDto.setId(theUser.get().getId());
				theUserDto.setUserName(theUser.get().getUserName());
				theUserDto.setFullName(theUser.get().getFullName());
				theUserDto.setEmail(theUser.get().getEmail());
				theUserDto.setPhone(theUser.get().getPhone());
				if(theUser.get().getRoles().size() == 2) {
					theUserDto.setRoleCode("ROLE_ADMIN");
				} else {
					theUserDto.setRoleCode("ROLE_EMPLOYEE");
				}
				return theUserDto;
			}
		}
		
		return null;
	}

	@Override
	public UserDto findByUserName(String userName) throws ResourceNotFoundException {
		
		Optional<User> theUser  = userRepository.findByUserName(userName);
		
		if(!theUser.isPresent()) {
			throw new ResourceNotFoundException("Not found user with username=" + userName);
		} else {
			if(theUser.get().getEnabled() == 1) {
				UserDto theUserDto = new UserDto();
				theUserDto.setId(theUser.get().getId());
				theUserDto.setUserName(theUser.get().getUserName());
				theUserDto.setFullName(theUser.get().getFullName());
				theUserDto.setEmail(theUser.get().getEmail());
				theUserDto.setPhone(theUser.get().getPhone());
				if(theUser.get().getRoles().size() == 2) {
					theUserDto.setRoleCode("ROLE_ADMIN");
				} else {
					theUserDto.setRoleCode("ROLE_EMPLOYEE");
				}
				return theUserDto;
			}
		}
		
		return null;
		
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Not found user with email=" + email));
	}
	
	@Override
	public MessageResponse createUser(UserDto theUserDto) {
		
		User theUser = new User();
		
		theUser.setCreatedDate(new Date());
		theUser.setUserName(theUserDto.getUserName());
		theUser.setFullName(theUserDto.getFullName());
		theUser.setPassword(passwordEncoder.encode(theUserDto.getPassword()));
		theUser.setEmail(theUserDto.getEmail());
		theUser.setPhone(theUserDto.getPhone());
		theUser.setEnabled(1);
		
		if(theUserDto.getRoleCode().equals("ROLE_ADMIN")) {
			Set<Role> roles = new HashSet<>(Arrays.asList(roleRepository.findByCode("ROLE_EMPLOYEE"),
					roleRepository.findByCode("ROLE_ADMIN")));
			theUser.setRoles(roles);
		} else {
			Set<Role> roles = new HashSet<>(Arrays.asList(roleRepository.findByCode("ROLE_EMPLOYEE")));
			theUser.setRoles(roles);
		}
		
		userRepository.save(theUser);
		
		return new MessageResponse("Create user successfully!");
	}

	@Override
	public MessageResponse updateUser(Long theId, UserDto theUserDto) throws ResourceNotFoundException {
		
		Optional<User> theUser = userRepository.findById(theId);
		
		if(!theUser.isPresent()) {
			throw new ResourceNotFoundException("Not found user with ID=" + theId);
		} else {
			theUser.get().setModifiedDate(new Date());
			theUser.get().setFullName(theUserDto.getFullName());
			theUser.get().setPhone(theUserDto.getPhone());
			theUser.get().setEnabled(1);
			
			if(theUserDto.getRoleCode().equals("ROLE_ADMIN")) {
				Set<Role> roles = new HashSet<>(Arrays.asList(roleRepository.findByCode("ROLE_EMPLOYEE"),
						roleRepository.findByCode("ROLE_ADMIN")));
				theUser.get().setRoles(roles);
				
			} else {
				Set<Role> roles = new HashSet<>(Arrays.asList(roleRepository.findByCode("ROLE_EMPLOYEE")));
				theUser.get().setRoles(roles);
			}
			
			userRepository.save(theUser.get());
		}
		
		return new MessageResponse("Update user successfully!");
	}

	@Override
	public void deleteUser(Long theId) throws ResourceNotFoundException {
		
		Optional<User> theUser = userRepository.findById(theId);
		
		if(!theUser.isPresent()) {
			throw new ResourceNotFoundException("Not found user with ID=" + theId);
		} else {
			theUser.get().setEnabled(0);
			userRepository.save(theUser.get());
		}

	}

	@Override
	public Boolean existsByEmail(String email) {
		
		return userRepository.existsByEmail(email);
	}

	@Override
	public Boolean existsByUserName(String username) {
		
		return userRepository.existsByUserName(username);
	}

	@Override
	public List<UserDto> search(String key) {
		key = key.toLowerCase();
		
		List<User> theUsers = userRepository.search(key);
		
		List<UserDto> theUsersDto = new ArrayList<UserDto>();
		
		for(User theUser :theUsers) {
			if(theUser.getEnabled() == 1) {
				UserDto theUserDto = new UserDto();
				theUserDto.setId(theUser.getId());
				theUserDto.setUserName(theUser.getUserName());
				theUserDto.setFullName(theUser.getFullName());
				theUserDto.setEmail(theUser.getEmail());
				theUserDto.setPhone(theUser.getPhone());
				if(theUser.getRoles().size() == 2) {
					theUserDto.setRoleCode("ROLE_ADMIN");
				} else {
					theUserDto.setRoleCode("ROLE_EMPLOYEE");
				}
				theUsersDto.add(theUserDto);
			}
		}
		
		return theUsersDto;
	}

}

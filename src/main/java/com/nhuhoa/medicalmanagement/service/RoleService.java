package com.nhuhoa.medicalmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhuhoa.medicalmanagement.entity.Role;
import com.nhuhoa.medicalmanagement.exception.ResourceNotFoundException;
import com.nhuhoa.medicalmanagement.repository.RoleRepository;

@Service
@Transactional
public class RoleService implements IRoleService {
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Map<String, String> findAllByMap() {
		
		Map<String, String> result = new HashMap<String, String>();
		Iterable<Role> entities = roleRepository.findAll();
		for(Role item: entities) {
			result.put(item.getCode(),item.getName());
		}
		return result;
	}

	@Override
	public List<Role> findAll() {
		
		return roleRepository.findAll();
	}

	@Override
	public Role findById(Long theId) throws ResourceNotFoundException {
		
		return roleRepository.findById(theId).orElseThrow(() -> new ResourceNotFoundException("Not found role with ID=" + theId));
	}

	@Override
	public Role findByCode(String code)  {
		
		return roleRepository.findByCode(code);
	}

}

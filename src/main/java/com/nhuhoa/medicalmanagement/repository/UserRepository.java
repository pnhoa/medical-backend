package com.nhuhoa.medicalmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nhuhoa.medicalmanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	//@Query("SELECT u FROM User u WHERE u.userName = ?1")
	Optional<User> findByUserName(String userName);
	
	Optional<User> findByEmail(String email);
	
	Boolean existsByUserName(String userName);
	
	Boolean existsByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE lower(u.userName) LIKE %?1%"
			+ " OR lower(u.fullName) LIKE %?1% OR lower(u.email) LIKE %?1% OR lower(u.phone) LIKE %?1%" )
	List<User> search( String key);

}

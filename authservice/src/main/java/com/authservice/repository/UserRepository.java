package com.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User>findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User>findByContact(String contact);

    Boolean  existsByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByContact(String contact);

}

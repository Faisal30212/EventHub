package com.eventhub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventhub.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    // Find a user using email during login and password reset.
    Optional<User> findByEmail(String email);
}
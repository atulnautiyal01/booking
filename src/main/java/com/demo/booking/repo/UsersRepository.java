package com.demo.booking.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.booking.model.User;

public interface UsersRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

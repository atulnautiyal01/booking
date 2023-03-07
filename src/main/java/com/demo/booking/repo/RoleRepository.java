package com.demo.booking.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.booking.model.EnumRole;
import com.demo.booking.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(EnumRole name);
}

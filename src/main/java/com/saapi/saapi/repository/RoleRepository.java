package com.saapi.saapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.saapi.saapi.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}

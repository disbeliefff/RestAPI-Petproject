package com.myprojects.petproject_restapi.repository;

import com.myprojects.petproject_restapi.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}

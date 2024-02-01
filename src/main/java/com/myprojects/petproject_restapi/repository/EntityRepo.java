package com.myprojects.petproject_restapi.repository;

import com.myprojects.petproject_restapi.entity.Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntityRepo extends CrudRepository<Entity, Long> {
    Optional<Entity> findByUsername(String username);

    void deleteById(int id);
}

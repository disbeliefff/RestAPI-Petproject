package com.myprojects.petproject_restapi.repository;

import com.myprojects.petproject_restapi.entity.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepo extends JpaRepository<Entity, Integer> {
}

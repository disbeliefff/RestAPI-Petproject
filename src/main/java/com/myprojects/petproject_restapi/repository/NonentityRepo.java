package com.myprojects.petproject_restapi.repository;

import com.myprojects.petproject_restapi.entity.Nonentity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NonentityRepo extends MongoRepository<Nonentity, UUID> {

    Nonentity findByName(String name);

}

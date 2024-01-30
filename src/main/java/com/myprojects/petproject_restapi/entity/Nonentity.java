package com.myprojects.petproject_restapi.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document
public class Nonentity {

    @Id
    private UUID id;

    @Indexed(unique = true)
    private String name;

    private Integer age;
}

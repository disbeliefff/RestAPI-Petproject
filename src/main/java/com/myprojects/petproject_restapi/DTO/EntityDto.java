package com.myprojects.petproject_restapi.DTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults (level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class EntityDto {

    private String username;

    private Long id;

    private String email;

    private int weight;

    private int age;
}

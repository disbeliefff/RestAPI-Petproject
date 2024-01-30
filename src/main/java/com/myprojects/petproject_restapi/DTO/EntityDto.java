package com.myprojects.petproject_restapi.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults (level = AccessLevel.PRIVATE)
public class EntityDto {

    String name;

    int weight;

    int age;
}

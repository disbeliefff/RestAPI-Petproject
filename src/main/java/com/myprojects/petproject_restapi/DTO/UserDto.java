package com.myprojects.petproject_restapi.DTO;

import com.myprojects.petproject_restapi.entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
}



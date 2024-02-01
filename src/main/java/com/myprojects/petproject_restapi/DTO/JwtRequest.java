package com.myprojects.petproject_restapi.DTO;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}

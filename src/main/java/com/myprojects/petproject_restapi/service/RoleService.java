package com.myprojects.petproject_restapi.service;

import com.myprojects.petproject_restapi.entity.Role;
import com.myprojects.petproject_restapi.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public Role getUserRole () {
        return roleRepo.findByName("ROLE_USER").get();
    }
}

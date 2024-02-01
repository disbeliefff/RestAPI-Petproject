package com.myprojects.petproject_restapi.service;

import com.myprojects.petproject_restapi.DTO.RegistrationUserDto;
import com.myprojects.petproject_restapi.entity.Entity;
import com.myprojects.petproject_restapi.repository.EntityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntityService implements UserDetailsService {

    private EntityRepo entityRepo;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setEntityRepo(EntityRepo entityRepo) {
        this.entityRepo = entityRepo;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Entity> findByUsername(String username) {
        return entityRepo.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Entity entity = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", username)
        ));
        return new org.springframework.security.core.userdetails.User(
                entity.getUsername(),
                entity.getPassword(),
                entity.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    public Entity createNewUser(RegistrationUserDto registrationUserDto) {
        Entity entity = new Entity();
        entity.setUsername(registrationUserDto.getUsername());
        entity.setEmail(registrationUserDto.getEmail());
        entity.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        entity.setRoles(List.of(roleService.getUserRole()));
        return entityRepo.save(entity);

    }
}
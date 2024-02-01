package com.myprojects.petproject_restapi.service;

import com.myprojects.petproject_restapi.DTO.JwtRequest;
import com.myprojects.petproject_restapi.DTO.JwtResponse;
import com.myprojects.petproject_restapi.DTO.RegistrationUserDto;
import com.myprojects.petproject_restapi.DTO.UserDto;
import com.myprojects.petproject_restapi.entity.Entity;
import com.myprojects.petproject_restapi.exceptions.AppError;
import com.myprojects.petproject_restapi.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EntityService entityService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                    authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"),
                    HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = entityService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    public ResponseEntity<?> createNewUser (@RequestBody RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Incorrect password"), HttpStatus.BAD_REQUEST);
        }
        if (entityService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User exists"), HttpStatus.BAD_REQUEST);
        }
        Entity entity = entityService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(entity.getId(),entity.getUsername(),entity.getEmail()));
    }
}


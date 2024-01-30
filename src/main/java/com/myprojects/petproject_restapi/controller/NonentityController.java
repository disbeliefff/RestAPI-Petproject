package com.myprojects.petproject_restapi.controller;

import com.myprojects.petproject_restapi.entity.Nonentity;
import com.myprojects.petproject_restapi.repository.NonentityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/non")
@RequiredArgsConstructor
public class NonentityController {

    private final NonentityRepo nonentityRepo;

    @GetMapping("/all")
    public List<Nonentity> findAllNonsFromDB(){
        return nonentityRepo.findAll();
    }

    @GetMapping
    public ResponseEntity<Nonentity> getNonByName(@RequestParam String name) {
        var nonEntity = nonentityRepo.findByName(name);
        if (nonEntity == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return new ResponseEntity<>(nonEntity, HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public Nonentity putNonIntoDB(@RequestBody Nonentity nonentity){
        nonentity.setId(UUID.randomUUID());
        return nonentityRepo.save(nonentity);
    }
}

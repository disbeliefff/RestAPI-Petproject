package com.myprojects.petproject_restapi.controller;

import com.myprojects.petproject_restapi.DTO.EntityDto;
import com.myprojects.petproject_restapi.entity.Entity;
import com.myprojects.petproject_restapi.repository.EntityRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main_methods")
@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final EntityRepo entityRepo;

    @Operation (
            summary = "Кладет новую сущность в базу",
            description = "Получает DTO сущности и билдером собирает и сохраняет сущность в базу"
    )
    @PostMapping("/api/add")
    public void addUser(@RequestBody EntityDto entityDto) {
        log.info(
                "New Entity: " + entityRepo.save(
                        Entity.builder()
                                .age(entityDto.getAge())
                                .name(entityDto.getName())
                                .weight(entityDto.getWeight())
                                .build())
        );
    }

    @SneakyThrows
    @GetMapping("/api/all")
        public List<Entity> getAll() {
            return entityRepo.findAll();
        }
    @GetMapping("/api")
    public Entity getEntity (@RequestParam int id) {
        return entityRepo.findById(id).orElseThrow();
    }
    @DeleteMapping("/api")
    public void deleteEntity (@RequestParam int id) {
        entityRepo.deleteById(id);
    }

    @PutMapping("/api/put")
    public String changeEntity(@RequestBody Entity entity) {
        if (!entityRepo.existsById(entity.getId())) {
            return "No such entity";
        }
        return entityRepo.save(entity).toString();
    }
}


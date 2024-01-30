package com.myprojects.petproject_restapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
@jakarta.persistence.Entity
@Table(name = "entityes")
@Getter
@Setter
public class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String name;

    private int age;

    private int weight;

    public Entity(String name, int age, int weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public Entity() {
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }
}


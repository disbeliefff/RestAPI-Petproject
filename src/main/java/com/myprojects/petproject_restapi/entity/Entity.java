package com.myprojects.petproject_restapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@Builder
@jakarta.persistence.Entity
@Table(name = "entityes")
@Getter
@Setter
public class Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    private int age;

    private int weight;

    public Entity(String name, int age, int weight) {
        this.username = username;
        this.age = age;
        this.weight = weight;
    }

    public Entity() {
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;
}



package com.personal.projects.lovable_clone.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@FieldDefaults(level= AccessLevel.PRIVATE) //We can get rid of all private declerations
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    Long id;
    String email;
    String passwordHash;
    String name;
    String avatarUrl;
    //LocalDateTime createdAt;
    Instant createdAt;
    Instant UpdatedAt;
    Instant deletedAt;

}

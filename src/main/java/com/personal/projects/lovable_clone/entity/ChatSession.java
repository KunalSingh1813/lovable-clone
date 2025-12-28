package com.personal.projects.lovable_clone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level= AccessLevel.PRIVATE) //We can get rid of all private declerations
@Getter
@Setter
public class ChatSession {
    Project project;
    User user;

    String title;

    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;
}

package com.personal.projects.lovable_clone.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level= AccessLevel.PRIVATE) //We can get rid of all private declerations
@Getter
@Setter
public class UsageLog {
    Long id;
    User user;
    Project project;
    String action;

    Integer tokensUsed;
    Integer durationMs;

    String metaData; //JSON of{model_used , prompt_used}

    Instant createdAt;
}

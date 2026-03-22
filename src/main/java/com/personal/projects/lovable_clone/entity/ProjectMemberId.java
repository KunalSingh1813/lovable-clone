package com.personal.projects.lovable_clone.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE) //We can get rid of all private declerations
@Getter
@Setter
@Embeddable
public class ProjectMemberId {
    Long projectId;
    Long userId;
}

package com.personal.projects.lovable_clone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level= AccessLevel.PRIVATE) //We can get rid of all private declerations
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ProjectMemberId {
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "user_id")
    private Long userId;

    public ProjectMemberId(Long projectId, Long id) {
    }
}

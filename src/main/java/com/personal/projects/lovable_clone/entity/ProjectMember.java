package com.personal.projects.lovable_clone.entity;

import com.personal.projects.lovable_clone.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;


@FieldDefaults(level= AccessLevel.PRIVATE) //We can get rid of all private declerations
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "project_members")
public class ProjectMember {
    @EmbeddedId //this will create composite Id
    ProjectMemberId id;

    @ManyToOne
    @MapsId("projectId") //will create a FK constraint
    Project project;

    @ManyToOne
    @MapsId("userId")
    User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProjectRole projectRole;

    Instant invitedAt;

    Instant acceptedAt;
}

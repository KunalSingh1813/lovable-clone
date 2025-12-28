package com.personal.projects.lovable_clone.entity;

import com.personal.projects.lovable_clone.enums.PreviewStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level= AccessLevel.PRIVATE) //We can get rid of all private declerations
@Getter
@Setter
public class Preview {
    Long id;

    Project project;

    String namespace;

    String podName; //Pods are the smallest unit that you can control in a Kubernetes Clustur.

    String previewUrl;

    PreviewStatus status;

    Instant startedAt;
    Instant terminatedAt;

    Instant createdAt;
}

package com.personal.projects.lovable_clone.mapper;

import com.personal.projects.lovable_clone.dto.auth.UserProfileResponse;
import com.personal.projects.lovable_clone.dto.project.ProjectResponse;
import com.personal.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.personal.projects.lovable_clone.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring" )//,uses = UserMapper.class)
public interface ProjectMapper {
    ProjectResponse toProjectResponse(Project project);
    @Mapping(source = "name", target = "projectName")
    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> project);
}

package com.personal.projects.lovable_clone.service.impl;

import com.personal.projects.lovable_clone.dto.project.ProjectRequest;
import com.personal.projects.lovable_clone.dto.project.ProjectResponse;
import com.personal.projects.lovable_clone.dto.project.ProjectSummaryResponse;
import com.personal.projects.lovable_clone.entity.Project;
import com.personal.projects.lovable_clone.entity.ProjectMember;
import com.personal.projects.lovable_clone.entity.ProjectMemberId;
import com.personal.projects.lovable_clone.entity.User;
import com.personal.projects.lovable_clone.enums.ProjectRole;
import com.personal.projects.lovable_clone.error.ResourceNotFoundException;
import com.personal.projects.lovable_clone.mapper.ProjectMapper;
import com.personal.projects.lovable_clone.repository.ProjectMemberRepository;
import com.personal.projects.lovable_clone.repository.ProjectRepository;
import com.personal.projects.lovable_clone.repository.UserRepository;
import com.personal.projects.lovable_clone.service.ProjectService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)

@Transactional
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;
    ProjectMemberRepository projectMemberRepository;
    @Override
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId.toString())
        );

        Project project = Project.builder()
                .name(request.name())
                .isPublic(false)
                .build();

        // 1. Save project first to generate the ID
        project = projectRepository.save(project);

        // 2. Create the project member association using the generated ID
        ProjectMemberId projectMemberId = new ProjectMemberId(project.getId(), owner.getId());
        ProjectMember projectMember = ProjectMember.builder()
                .id(projectMemberId)
                .projectRole(ProjectRole.OWNER)
                .user(owner)
                .acceptedAt(Instant.now())
                .invitedAt(Instant.now())
                .project(project)
                .build();

        projectMemberRepository.save(projectMember);

        return projectMapper.toProjectResponse(project);
    }

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
//        return projectRepository.findAllAccessibleByUser(userId)
//                .stream()
//                .map(projectMapper::toProjectSummaryResponse)
//                .collect(Collectors.toList());
    var projects = projectRepository.findAllAccessibleByUser(userId);
    return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = projectRepository.findAccessibleProjectById(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", id.toString()));
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
       // Project project = projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
        Project  project = getAccessibleProjectById(id,userId);

        project.setName(request.name());
        project = projectRepository.save(project);
        
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id, Long userId) {
        Project  project = getAccessibleProjectById(id,userId);
//        if(!project.getOwner().getId().equals(userId)){
//            throw new RuntimeException("You are not allowed to delete");
//        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }


    ///INTERNAL FUNCTION
    public Project getAccessibleProjectById(Long projectId, Long userId)
    {
        return projectRepository.findAccessibleProjectById(projectId,userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project",projectId.toString()));
    }
}

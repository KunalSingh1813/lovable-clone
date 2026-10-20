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
import com.personal.projects.lovable_clone.security.AuthUtil;
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
    AuthUtil authutil;
    @Override
    public ProjectResponse createProject(ProjectRequest request) {
        Long userId = authutil.getCurrentUserId();
        //this will make a DB call
//        User owner = userRepository.findById(userId).orElseThrow(
//                () -> new ResourceNotFoundException("User", userId.toString())
//        );
        //This will not make a databse call
        User owner = userRepository.getReferenceById(userId); //Creates a Hibernate Proxy object. use withing Transactional context.
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
    public List<ProjectSummaryResponse> getUserProjects() {
        Long userId = authutil.getCurrentUserId();
    var projects = projectRepository.findAllAccessibleByUser(userId);
    return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id) {
        Long userId = authutil.getCurrentUserId();
        Project project = projectRepository.findAccessibleProjectById(id, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", id.toString()));
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
       // Project project = projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
        Long userId = authutil.getCurrentUserId();
        Project  project = getAccessibleProjectById(id);

        project.setName(request.name());
        project = projectRepository.save(project);
        
        return projectMapper.toProjectResponse(project);
    }

    @Override
    public void softDelete(Long id) {
        Long userId = authutil.getCurrentUserId();
        Project  project = getAccessibleProjectById(id);
//        if(!project.getOwner().getId().equals(userId)){
//            throw new RuntimeException("You are not allowed to delete");
//        }
        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }


    ///INTERNAL FUNCTION
    public Project getAccessibleProjectById(Long projectId)
    {
        Long userId = authutil.getCurrentUserId();
        return projectRepository.findAccessibleProjectById(projectId,userId)
                .orElseThrow(() -> new ResourceNotFoundException("Project",projectId.toString()));
    }
}

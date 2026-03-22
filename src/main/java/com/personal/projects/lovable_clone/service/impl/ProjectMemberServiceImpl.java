package com.personal.projects.lovable_clone.service.impl;

import com.personal.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.personal.projects.lovable_clone.dto.member.MemberResponse;
import com.personal.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.personal.projects.lovable_clone.entity.Project;
import com.personal.projects.lovable_clone.mapper.ProjectMemberMapper;
import com.personal.projects.lovable_clone.repository.ProjectMemberRepository;
import com.personal.projects.lovable_clone.repository.ProjectRepository;
import com.personal.projects.lovable_clone.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProjectMemberServiceImpl implements ProjectMemberService {
    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    @Override
    public List<MemberResponse> getProjectMembers(Long projectId, Long userId) {
        Project project = getAccessibleProjectById(projectId,userId);

        List<MemberResponse> memberResponseList = new ArrayList<>();
        memberResponseList.add(projectMemberMapper.toProjectMemberResponseFromOwner(project.getOwner()));

        memberResponseList.addAll(projectMemberRepository.findByIdProjectId(projectId)
                .stream()
                .map(projectMemberMapper::toProjectMemberResponseFromMember)
                .toList());

        return memberResponseList;
    }

    @Override
    public MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        return null;
    }

    @Override
    public MemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId) {
        return null;
    }

    public Project getAccessibleProjectById(Long projectId, Long userId)
    {
        return projectRepository.findAccessibleProjectById(projectId,userId).orElseThrow();
    }
}

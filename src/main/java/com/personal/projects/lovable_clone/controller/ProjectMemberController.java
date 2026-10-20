package com.personal.projects.lovable_clone.controller;

import com.personal.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.personal.projects.lovable_clone.dto.member.MemberResponse;
import com.personal.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.personal.projects.lovable_clone.entity.ProjectMember;
import com.personal.projects.lovable_clone.service.ProjectMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
//@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService; //Constructor Dependency Injection

    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    //API to get all members
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getProjectMembers(@PathVariable Long projectId){

        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId));
    }

    //API to invite by email
    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(
            @PathVariable Long projectId,
            @RequestBody @Valid InviteMemberRequest request
    ){

        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteMember(projectId, request)
        );
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @RequestBody @Valid UpdateMemberRoleRequest request
            ){

        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId,memberId,request));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> removeMember(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ){

        projectMemberService.removeProjectMember(projectId,memberId);
        return ResponseEntity.noContent().build();
    }

}

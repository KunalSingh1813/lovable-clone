package com.personal.projects.lovable_clone.controller;

import com.personal.projects.lovable_clone.dto.member.InviteMemberRequest;
import com.personal.projects.lovable_clone.dto.member.MemberResponse;
import com.personal.projects.lovable_clone.dto.member.UpdateMemberRoleRequest;
import com.personal.projects.lovable_clone.entity.ProjectMember;
import com.personal.projects.lovable_clone.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects/{projectId}/members")
@RequiredArgsConstructor
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService; //Constructor Dependency Injection

    //API to get all members
    @GetMapping
    public ResponseEntity<List<ProjectMember>> getProjectMembers(@PathVariable Long projectId){
        Long userId =1L;
        return ResponseEntity.ok(projectMemberService.getProjectMembers(projectId, userId));
    }

    //API to invite by email
    @PostMapping
    public ResponseEntity<MemberResponse> inviteMember(
            @PathVariable Long projectId,
            @RequestBody InviteMemberRequest request
    ){
        Long userId = 1L;
        return ResponseEntity.status(HttpStatus.CREATED).body(
                projectMemberService.inviteMember(projectId, request, userId)
        );
    }

    @PatchMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId,
            @RequestBody UpdateMemberRoleRequest request
            ){
        Long userId =1L;
        return ResponseEntity.ok(projectMemberService.updateMemberRole(projectId,memberId,request, userId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<MemberResponse> updateMemberRole(
            @PathVariable Long projectId,
            @PathVariable Long memberId
    ){
        Long userId =1L;
        return ResponseEntity.ok(projectMemberService.deleteProjectMember(projectId,memberId, userId));
    }

}

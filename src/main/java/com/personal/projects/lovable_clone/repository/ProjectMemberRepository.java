package com.personal.projects.lovable_clone.repository;

import com.personal.projects.lovable_clone.entity.ProjectMember;
import com.personal.projects.lovable_clone.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, ProjectMemberId> {

    List<ProjectMember> findByIdProjectId(long projectId);
}

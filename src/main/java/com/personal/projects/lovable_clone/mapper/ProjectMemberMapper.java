package com.personal.projects.lovable_clone.mapper;

import com.personal.projects.lovable_clone.dto.member.MemberResponse;
import com.personal.projects.lovable_clone.entity.ProjectMember;
import com.personal.projects.lovable_clone.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {
    @Mapping(target = "userId", source = "id")
    @Mapping(target = "projectRole", constant = "OWNER")
    MemberResponse toProjectMemberResponseFromOwner(User owner);

    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);
}

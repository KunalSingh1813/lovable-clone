package com.personal.projects.lovable_clone.service;

import com.personal.projects.lovable_clone.dto.auth.UserProfileResponse;

public interface UserService {
    UserProfileResponse getProfile(Long userId);
}

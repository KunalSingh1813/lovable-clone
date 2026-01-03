package com.personal.projects.lovable_clone.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse user)
{

}


//dummy: new AuthResponse("",new UserProfileResponse())
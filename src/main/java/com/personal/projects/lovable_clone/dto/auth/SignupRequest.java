package com.personal.projects.lovable_clone.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(@Email @NotBlank String email,
                            @NotBlank String name,
                            @Size(min=4) String password
) {
}

package com.pms.user_server.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserLoginRequest(
        @NotNull(message = "User email is required")
        @Email(message = "Give the valid email")
        String email,
        @NotNull(message = "Password must be include")
        String password
) { }

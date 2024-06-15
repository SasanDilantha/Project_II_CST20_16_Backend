package com.pms.user_server.dto;

import com.pms.user_server.model.Manager;
import com.pms.user_server.model.Role;
import com.pms.user_server.model.Veterinarian;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        Integer user_id,
        @NotNull(message = "User firstname is required")
        String first_name,
        @NotNull(message = "User lastname is required")
        String last_name,
        @NotNull(message = "User email is required")
        @Email(message = "Give the valid email")
        String email,
        @NotNull(message = "Mobile number is required")
        String phone,
        @NotNull(message = "Password must be include")
        String password,
        @NotNull(message = "User should have role")
        Role role,
        String farm_code
) { }

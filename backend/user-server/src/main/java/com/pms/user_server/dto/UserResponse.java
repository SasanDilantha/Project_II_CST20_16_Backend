package com.pms.user_server.dto;


public record UserResponse(
        String user_id,
        String first_name,
        String last_name,
        String email,
        String phone,
        String password,
        String role,
        String managerFarmId,
        String veterinarianFarmId
) { }

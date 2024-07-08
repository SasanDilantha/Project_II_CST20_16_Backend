package com.pms.user_server.dto;


public record UserResponse(
        Integer user_id,
        String first_name,
        String last_name,
        String email,
        String phone,
        String password,
        String role,
        Integer expense_id,
        String managerFarmId,
        String veterinarianFarmId
) { }

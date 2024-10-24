package com.pms.user_server.dto.ui.users;

public record UserUiResponse(
        Integer user_id,
        String first_name,
        String last_name,
        String email,
        String phone,
        String role,
        String farm_name,
        String farm_code,
        String salary

) { }

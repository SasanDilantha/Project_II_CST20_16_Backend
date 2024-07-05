package com.pms.user_server.dto.ui;

import com.pms.user_server.model.Role;


public record UserUpadeRequest(
        Integer user_id,
        String first_name,
        String last_name,
        String email,
        String phone,
        String password,
        Role role,
        String farm_code
) {
}

package com.pms.user_server.dto.client;

public record ToUserDetails(
        Integer farm_id,
        String farm_name,
        String farm_code
) {
}

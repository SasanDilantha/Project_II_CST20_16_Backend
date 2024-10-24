package com.pms.user_server.dto;

public record CheckPassword(
        String password,
        Integer userId
) {}

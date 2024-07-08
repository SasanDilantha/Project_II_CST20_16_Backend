package com.pms.user_server.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) { }

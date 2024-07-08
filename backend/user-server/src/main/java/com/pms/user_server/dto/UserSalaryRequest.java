package com.pms.user_server.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record UserSalaryRequest(
        @NotNull(message = "User id must have to done this task")
        Integer user_id,
        @NotNull(message = "Farm id must have to done this task")
        String farm_code,
        @Positive(message = "Invalid Salary input ")
        @NotNull(message = "Salary value must have to done this task")
        BigDecimal salary
) {
}

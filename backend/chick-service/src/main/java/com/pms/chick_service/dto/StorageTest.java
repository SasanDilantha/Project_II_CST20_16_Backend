package com.pms.chick_service.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record StorageTest(
        Integer chick_storage_id,
        Float weight
) { }

package com.pms.chick_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ChickSupplierRequest(
        Integer chick_supplier_id,
        @NotNull(message = "Supplier should have a name")
        String supplier_name,
        @NotNull(message = "Supplier should have a mobile number")
        String supplier_phone,
        @Email(message = "Supplier should have a email")
        String supplier_email,
        @NotNull(message = "Supplier should have a address")
        String supplier_address
) { }

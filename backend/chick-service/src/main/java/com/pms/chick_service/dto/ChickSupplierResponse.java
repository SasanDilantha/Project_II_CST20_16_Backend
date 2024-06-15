package com.pms.chick_service.dto;

public record ChickSupplierResponse(
        Integer chick_supplier_id,
        String supplier_name,
        String supplier_phone,
        String supplier_email,
        String supplier_address
) {
}

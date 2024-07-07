package com.pms.medication_service.dto;

public record MedicationSupplierResponse(
        Integer med_supplier_id,
        String supplier_name,
        String supplier_phone,
        String supplier_email,
        String supplier_address
) {
}

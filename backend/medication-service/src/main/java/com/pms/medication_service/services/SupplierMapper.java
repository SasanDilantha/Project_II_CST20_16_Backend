package com.pms.medication_service.services;

import com.pms.medication_service.dto.MedicationSupplierResponse;
import com.pms.medication_service.dto.MeditationSupplierRequest;
import com.pms.medication_service.model.MedicationSupplier;
import org.springframework.stereotype.Service;

@Service
public class SupplierMapper {
    public MedicationSupplier toSupplier(MeditationSupplierRequest request) {
        return MedicationSupplier.builder()
                .medication_supplier_id(request.med_supplier_id())
                .supplier_name(request.supplier_name())
                .supplier_phone(request.supplier_phone())
                .supplier_email(request.supplier_email())
                .supplier_address(request.supplier_address())
                .build();
    }

    public MedicationSupplierResponse fromSupplier(MedicationSupplier feedSupplier) {
        return new MedicationSupplierResponse(
                feedSupplier.getMedication_supplier_id(),
                feedSupplier.getSupplier_name(),
                feedSupplier.getSupplier_phone(),
                feedSupplier.getSupplier_email(),
                feedSupplier.getSupplier_address()
        );
    }
}

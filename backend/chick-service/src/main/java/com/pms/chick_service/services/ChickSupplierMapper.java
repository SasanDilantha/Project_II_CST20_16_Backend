package com.pms.chick_service.services;

import com.pms.chick_service.dto.ChickSupplierRequest;
import com.pms.chick_service.dto.ChickSupplierResponse;
import com.pms.chick_service.model.ChickSupplier;
import org.springframework.stereotype.Service;

@Service
public class ChickSupplierMapper {
    public ChickSupplier toSupplier(ChickSupplierRequest request) {
        return ChickSupplier.builder()
                .chick_supplier_id(request.chick_supplier_id())
                .supplier_name(request.supplier_name())
                .supplier_phone(request.supplier_phone())
                .supplier_email(request.supplier_email())
                .supplier_address(request.supplier_address())
                .build();
    }

    public ChickSupplierResponse fromSupplier(ChickSupplier chickSupplier) {
        return new ChickSupplierResponse(
                chickSupplier.getChick_supplier_id(),
                chickSupplier.getSupplier_name(),
                chickSupplier.getSupplier_phone(),
                chickSupplier.getSupplier_email(),
                chickSupplier.getSupplier_address()
        );
    }
}

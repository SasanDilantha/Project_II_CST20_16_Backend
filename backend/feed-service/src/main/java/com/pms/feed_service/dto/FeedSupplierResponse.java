package com.pms.feed_service.dto;

public record FeedSupplierResponse(
        Integer feed_supplier_id,
        String supplier_name,
        String supplier_phone,
        String supplier_email,
        String supplier_address
) {
}

package com.pms.feed_service.services;

import com.pms.feed_service.dto.FeedSupplierRequest;
import com.pms.feed_service.dto.FeedSupplierResponse;
import com.pms.feed_service.model.FeedSupplier;
import org.springframework.stereotype.Service;

@Service
public class SupplierMapper {
    public FeedSupplier toSupplier(FeedSupplierRequest request) {
        return FeedSupplier.builder()
                .feed_supplier_id(request.feed_supplier_id())
                .supplier_name(request.supplier_name())
                .supplier_phone(request.supplier_phone())
                .supplier_email(request.supplier_email())
                .supplier_address(request.supplier_address())
                .build();
    }

    public FeedSupplierResponse fromSupplier(FeedSupplier feedSupplier) {
        return new FeedSupplierResponse(
                feedSupplier.getFeed_supplier_id(),
                feedSupplier.getSupplier_name(),
                feedSupplier.getSupplier_phone(),
                feedSupplier.getSupplier_email(),
                feedSupplier.getSupplier_address()
        );
    }
}

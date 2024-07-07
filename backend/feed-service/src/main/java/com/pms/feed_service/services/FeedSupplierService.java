package com.pms.feed_service.services;

import com.pms.feed_service.dto.FeedSupplierRequest;
import com.pms.feed_service.dto.FeedSupplierResponse;
import com.pms.feed_service.repository.FeedSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedSupplierService {
    private final FeedSupplierRepository feedSupplierRepository;
    private final SupplierMapper supplierMapper;

    // create feed supplier
    public String createSupplier(FeedSupplierRequest request) {
        var supplier = feedSupplierRepository.save(supplierMapper.toSupplier(request));
        return "Successfully created supplier: " + supplier.getSupplier_name();
    }

    // get supplier id for using name nda phone
    public Integer findSupplierId(String supplierName, String supplierPhoneNumber) {
        return feedSupplierRepository.findIdByNameAndPhoneNumber(supplierName,supplierPhoneNumber);
    }

    // get all supplier details
    public List<FeedSupplierResponse> getAllSupplier() {
        return feedSupplierRepository.findAll()
                .stream()
                .map(supplierMapper::fromSupplier)
                .collect(Collectors.toList());
    }
}

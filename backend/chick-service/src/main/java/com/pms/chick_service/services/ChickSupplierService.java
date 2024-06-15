package com.pms.chick_service.services;

import com.pms.chick_service.dto.ChickSupplierRequest;
import com.pms.chick_service.dto.ChickSupplierResponse;
import com.pms.chick_service.repository.ChickSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChickSupplierService {
    private final ChickSupplierRepository chickSupplierRepository;
    private final ChickSupplierMapper mapper;

    // create supplier
    public String createSupplier(ChickSupplierRequest request) {
        var supplier = chickSupplierRepository.save(mapper.toSupplier(request));
        return "Successfully created supplier: " + supplier.getSupplier_name();
    }

    // get supplier id for using name nda phone
    public Integer findSupplierId(String supplierName, String supplierPhoneNumber) {
        return chickSupplierRepository.findIdByNameAndPhoneNumber(supplierName,supplierPhoneNumber);
    }

    public List<ChickSupplierResponse> getAllSupplier() {
        return chickSupplierRepository.findAll()
                .stream()
                .map(mapper::fromSupplier)
                .collect(Collectors.toList());
    }
}

package com.pms.medication_service.services;

import com.pms.medication_service.dto.MedicationSupplierResponse;
import com.pms.medication_service.dto.MeditationSupplierRequest;
import com.pms.medication_service.repository.MedicationSupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MeditationSupplierService {
    private final MedicationSupplierRepository medSupplierRepository;
    private final SupplierMapper supplierMapper;

    // create feed supplier
    public String createSupplier(MeditationSupplierRequest request) {
        var supplier = medSupplierRepository.save(supplierMapper.toSupplier(request));
        return "Successfully created supplier: " + supplier.getSupplier_name();
    }

    // get supplier id for using name nda phone
    public Integer findSupplierId(String supplierName, String supplierPhoneNumber) {
        return medSupplierRepository.findIdByNameAndPhoneNumber(supplierName,supplierPhoneNumber);
    }

    // get all supplier details
    public List<MedicationSupplierResponse> getAllSupplier() {
        return medSupplierRepository.findAll()
                .stream()
                .map(supplierMapper::fromSupplier)
                .collect(Collectors.toList());
    }
}

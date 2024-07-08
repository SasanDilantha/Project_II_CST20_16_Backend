package com.pms.medication_service.services;

import com.pms.medication_service.dto.MedicationInventoryRequest;
import com.pms.medication_service.repository.MedicationStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final MedicationStorageRepository repository;
    private final MeditationMapper mapper;

    // create record of chick inventory request ----> save data to storage
    public Integer createStorage(MedicationInventoryRequest request){
        var storage = repository.save(mapper.toStorage(request));
        return storage.getMedication_id();
    }
}

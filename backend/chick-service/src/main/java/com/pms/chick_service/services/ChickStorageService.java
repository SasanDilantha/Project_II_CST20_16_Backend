package com.pms.chick_service.services;

import com.pms.chick_service.dto.ChickInventoryRequest;
import com.pms.chick_service.repository.ChickStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChickStorageService {
    private final ChickStorageRepository chickStorageRepository;
    private final ChickStorageMapper mapper;


    // create record of chick inventory request ----> save data to storage
    public Integer createStorage(ChickInventoryRequest request){
        var storage = chickStorageRepository.save(mapper.toStorage(request));
        return storage.getChick_storage_id();
    }
}

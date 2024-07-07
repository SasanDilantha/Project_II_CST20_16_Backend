package com.pms.chick_service.services;

import com.pms.chick_service.dto.ChickInventoryRequest;
import com.pms.chick_service.model.ChickStorage;
import org.springframework.stereotype.Service;

@Service
public class ChickStorageMapper {
    public ChickStorage toStorage(ChickInventoryRequest request) {
        return ChickStorage.builder()
                .chick_breed_id(request.chick_breed_id())
                .breed(request.breed())
                .chick_quantity(request.chick_quantity())
                .age(request.age())
                .build();
    }
}

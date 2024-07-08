package com.pms.feed_service.services;

import com.pms.feed_service.dto.FeedInventoryRequest;
import com.pms.feed_service.repository.FeedStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageService {
    private final FeedStorageRepository repository;
    private final FeedMapper mapper;

    // create record of chick inventory request ----> save data to storage
    public Integer createStorage(FeedInventoryRequest request){
        var storage = repository.save(mapper.toStorage(request));
        return storage.getFeed_id();
    }
}

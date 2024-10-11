package com.pms.chick_service.services;

import com.pms.chick_service.client.FarmClient;
import com.pms.chick_service.dto.ChickInventoryRequest;
import com.pms.chick_service.dto.StorageTest;
import com.pms.chick_service.dto.client.FromChickMortality;
import com.pms.chick_service.model.ChickStorage;
import com.pms.chick_service.repository.ChickStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChickStorageService {
    private final ChickStorageRepository chickStorageRepository;
    private final ChickStorageMapper mapper;

    private final ChickInventoryService ins;

    private final FarmClient farmClient;

    // get init weight by placement id
    public Float testChickStorageService(Integer placementId){
        Integer inventoryId = ins.getInventoryIdByPlacementId(placementId);
        return chickStorageRepository.findInitWeightByPlacementId(placementId, inventoryId);
    }


    // create record of chick inventory request ----> save data to storage
    public Integer createStorage(ChickInventoryRequest request){
        var storage = chickStorageRepository.save(mapper.toStorage(request));
        return storage.getChick_storage_id();
    }

    // update chick age in storage
    //@Scheduled(fixedRate = 1000 * 15)
    @Scheduled(cron = "0 0 0 * * ?")// Run every day at midnight
    @Transactional
    public void updateAgeForChicks() {
        List<ChickStorage> chicks = chickStorageRepository.findBySellingDateIsNull();

        for (ChickStorage chickStorage : chicks) {
            chickStorage.setAge(chickStorage.getAge() + 1);
            chickStorageRepository.save(chickStorage);
        }
    }

    // send chick inventory initiate date
    public LocalDateTime getChickStorageInitById(Integer placementId){
        LocalDateTime date = chickStorageRepository.findStorageDateByPlacementId(placementId);
        return (date != null) ? date : null;
    }

    // get mortality details by using placement id for monitoring
    public FromChickMortality getMortalityDetails(Integer placementId) {
        var farmDetails = farmClient.getMortalityDetails(placementId);
        Integer inventoryId = ins.getInventoryIdByPlacementId(placementId);
        Integer initChickCount = chickStorageRepository.findInitChickCountByPlacementId(placementId, inventoryId);

        return new FromChickMortality(
                farmDetails.count(),
                initChickCount
        );
    }
}

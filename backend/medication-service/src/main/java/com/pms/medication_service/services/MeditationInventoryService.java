package com.pms.medication_service.services;

import com.pms.medication_service.client.ExpenseClient;
import com.pms.medication_service.client.FarmClient;
import com.pms.medication_service.dto.*;
import com.pms.medication_service.model.MedicationBlock;
import com.pms.medication_service.model.MedicationStorage;
import com.pms.medication_service.repository.MedicationBlockRepository;
import com.pms.medication_service.repository.MedicationInventoryCostRepository;
import com.pms.medication_service.repository.MedicationInventoryRepository;
import com.pms.medication_service.repository.MedicationStorageRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MeditationInventoryService {
    private static final Logger logger = LoggerFactory.getLogger(MeditationInventoryService.class);

    private final MedicationInventoryRepository medInventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final StorageService storageService;
    private final MedicationStorageRepository storageRepository;
    private final InventoryCostService costService;
    private final MedicationInventoryCostRepository inventoryCostRepository;
    private final MedicationBlockRepository bockRepository;
    private final ExpenseClient expenseClient;
    private final FarmClient farmClient;
    private final MeditationMapper feedMapper;


    // create or insert new feed inventory details
    public String toInventory(MedicationInventoryRequest request) {
        Integer med_id = storageService.createStorage(request);
        Integer feed_inventory_cost_id = costService.createInventoryCost(request);
        Integer expense_id = expenseClient.meditationInventory(inventoryMapper.toExpense(request));
        String med_inventory_code = createInventoryCode(request);
        var inventory = medInventoryRepository.save(feedMapper.toInventory(
                request,
                med_id,
                feed_inventory_cost_id,
                expense_id,
                med_inventory_code,
                storageRepository,
                inventoryCostRepository
        ));
        return inventory.getMedication_inventory_code();
    }

    public List<InventoryResponse> getAllInventory() {
        return medInventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::fromAllInventory)
                .collect(Collectors.toList());
    }

    public List<FarmUiResponse> forFarmDetails() {
        return medInventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::forFarmUi)
                .collect(Collectors.toList());
    }

    public String insertBlockDetails(BlockRequest request) {
        // get placement id  using code  ---> placement service
        Integer placement_id = farmClient.findPlacementIdByCode(request.placement_code());

        //save the record in block entity
        var block = bockRepository.save(feedMapper.toBlock(request,placement_id, storageRepository));

        return ("Successfully created chick inventory block::" + request.placement_code());
    }


    // create inventory code
    private String createInventoryCode(MedicationInventoryRequest request) {
        List<InventoryCode> inventoryCodeList = medInventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::fromInventory).toList();
        String setStringPart = request.med_type().name().substring(0,3);
        return generateCode(inventoryCodeList, setStringPart);
    }

    // generate code
    private String generateCode(List<InventoryCode> inventoryCodeList, String setStringPart) {
        Random rand = new Random();
        int max = 1000, min = 0;
        int numeric_part = rand.nextInt((max - min) + 1) + min;
        String inventory_code = setStringPart.toUpperCase()+"_"+numeric_part;
        // check code is available
        for (InventoryCode inventoryCode : inventoryCodeList) {
            if(inventoryCode.med_inventory_code().equals(inventory_code)) {
                generateCode(inventoryCodeList,setStringPart);
            }
        }

        return inventory_code;
    }

    public List<BlockDetails> getFeedInventoryDetails(Integer inventoryId) {
        // define return array
        List<BlockDetails> response = new ArrayList<>();

        // get storage id
        Integer storageId = medInventoryRepository.findStorageIdById(inventoryId);
        // get storage object by using storage id
        MedicationStorage storageObj = storageRepository.findById(storageId)
                .orElseThrow(() -> new RuntimeException("Storage with id " + storageId + " not found"));

        for(MedicationBlock feedBlock : storageObj.getMedicationBlocks()) {
            Integer id = feedBlock.getPlacement_id();
            try {
                // get placement code
                String placementCode = farmClient.findPlacementCodeById(id);
                // add all details to array
                response.add(inventoryMapper.toBlockDetails(feedBlock, placementCode));
            } catch (Exception e) {
                // Log the exception and continue with the next iteration
                logger.warn("Failed to get details for placement_id {}: {}", feedBlock.getPlacement_id(), e.getMessage());
            }
        }
        return response;
    }
}

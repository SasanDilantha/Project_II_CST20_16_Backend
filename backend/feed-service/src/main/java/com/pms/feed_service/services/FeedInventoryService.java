package com.pms.feed_service.services;

import com.pms.feed_service.client.ExpenseClient;
import com.pms.feed_service.client.FarmClient;
import com.pms.feed_service.dto.*;
import com.pms.feed_service.model.FeedBlock;
import com.pms.feed_service.model.FeedStorage;
import com.pms.feed_service.repository.FeedBockRepository;
import com.pms.feed_service.repository.FeedInventoryCostRepository;
import com.pms.feed_service.repository.FeedInventoryRepository;
import com.pms.feed_service.repository.FeedStorageRepository;
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
public class FeedInventoryService {
    private static final Logger logger = LoggerFactory.getLogger(FeedInventoryService.class);

    private final FeedInventoryRepository feedInventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final StorageService storageService;
    private final FeedStorageRepository storageRepository;
    private final InventoryCostService costService;
    private final FeedInventoryCostRepository inventoryCostRepository;
    private final FeedBockRepository bockRepository;
    private final ExpenseClient expenseClient;
    private final FarmClient farmClient;
    private final FeedMapper feedMapper;


    // create or insert new feed inventory details
    public String toInventory(FeedInventoryRequest request) {
        Integer feed_id = storageService.createStorage(request);
        Integer feed_inventory_cost_id = costService.createInventoryCost(request);
        Integer expense_id = expenseClient.createFeedInventory(inventoryMapper.toExpense(request));
        String feed_inventory_code = createInventoryCode(request);
        var inventory = feedInventoryRepository.save(feedMapper.toInventory(
                request,
                feed_id,
                feed_inventory_cost_id,
                expense_id,
                feed_inventory_code,
                storageRepository,
                inventoryCostRepository
        ));
        return inventory.getFeed_inventory_code();
    }

    public List<InventoryResponse> getAllInventory() {
        return feedInventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::fromAllInventory)
                .collect(Collectors.toList());
    }

    public List<FarmUiResponse> forFarmDetails() {
        return feedInventoryRepository.findAll()
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
    private String createInventoryCode(FeedInventoryRequest request) {
        List<InventoryCode> inventoryCodeList = feedInventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::fromInventory).toList();
        String setStringPart = request.feed_type().name().substring(0,3);
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
            if(inventoryCode.feed_inventory_code().equals(inventory_code)) {
                generateCode(inventoryCodeList,setStringPart);
            }
        }

        return inventory_code;
    }

    public List<BlockDetails> getFeedInventoryDetails(Integer inventoryId) {
        // define return array
        List<BlockDetails> response = new ArrayList<>();

        // get storage id
        Integer storageId = feedInventoryRepository.findStorageIdById(inventoryId);
        // get storage object by using storage id
        FeedStorage storageObj = storageRepository.findById(storageId)
                .orElseThrow(() -> new RuntimeException("Storage with id " + storageId + " not found"));

        for(FeedBlock feedBlock : storageObj.getFeedBlocks()) {
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

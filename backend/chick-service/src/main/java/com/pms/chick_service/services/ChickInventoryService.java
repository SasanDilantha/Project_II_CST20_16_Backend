package com.pms.chick_service.services;

import com.pms.chick_service.client.ExpenseClient;
import com.pms.chick_service.client.FarmClient;
import com.pms.chick_service.dto.*;
import com.pms.chick_service.dto.client.FromChickMortality;
import com.pms.chick_service.dto.client.ToChickBlockDetails;
import com.pms.chick_service.dto.ui.response.BlockDetails;
import com.pms.chick_service.dto.ui.response.FarmUiResponse;
import com.pms.chick_service.model.ChickBlock;
import com.pms.chick_service.model.ChickInventory;
import com.pms.chick_service.model.ChickStorage;
import com.pms.chick_service.repository.ChickBlockRepository;
import com.pms.chick_service.repository.ChickInventoryRepository;
import com.pms.chick_service.repository.ChickInventryCostRepository;
import com.pms.chick_service.repository.ChickStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class ChickInventoryService {
    private static final Logger logger = LoggerFactory.getLogger(ChickInventoryService.class);

    private final ChickInventoryRepository chickInventoryRepository;
    private final ChickInventoryMapper mapper;
    private final ChickStorageService storageService;
    private final ChickStorageRepository storageRepository;
    private final ChickInventryCostService costService;
    private final ChickInventryCostRepository costRepository;
    private final ChickBlockRepository blockRepository;
    private final ExpenseClient expenseClient;
    private final FarmClient farmClient;

    // create inventory
    public String toInventory(ChickInventoryRequest request) {
        Integer chick_breed_id = storageService.createStorage(request);
        Integer chick_inventory_cost_id = costService.createInventryCost(request);
        Integer expense_id = expenseClient.createChickInventory(mapper.toExpense(request));
        String chick_inventory_code = createInventoryCode(request);
        var inventory = chickInventoryRepository.save(mapper.toInventory(
                request.chick_inventory_id(),
                request.chick_quantity(),
                chick_breed_id,
                chick_inventory_cost_id,
                expense_id,chick_inventory_code,
                storageRepository,
                costRepository
        ));
        return inventory.getChick_inventory_code();
    }

    // create inventory code
    private String createInventoryCode(ChickInventoryRequest request) {
        List<InvetoryCode> invetoryCodeList = chickInventoryRepository.findAll()
                .stream()
                .map(mapper::fromInventory).toList();
        String setStringPart = request.breed().name().substring(0,3);
        return genarateCode(invetoryCodeList, setStringPart);
    }

    // generate code
    private String genarateCode(List<InvetoryCode> invetoryCodeList, String setStringPart) {
        Random rand = new Random();
        int max = 1000, min = 0;
        int numeric_part = rand.nextInt((max - min) + 1) + min;
        String inventory_code = setStringPart.toUpperCase()+"_"+numeric_part;
        // check code is available
        for (InvetoryCode invetoryCode : invetoryCodeList) {
            if(invetoryCode.chick_inventory_code().equals(inventory_code)) {
                genarateCode(invetoryCodeList,setStringPart);
            }
        }

        return inventory_code;
    }

    // update the available chick count base on mortality and return new available chick count
    @Transactional
    public Integer updateAvailableChick(MortalityRequest request) {
        // update available count in inventory
        int rows_updates = chickInventoryRepository.updateAvailableQuantity(request.mortality_quantity(),request.placement_id());

        if(rows_updates > 0) {
            var getInvetory = chickInventoryRepository.findAvailableQuantityByPlacementId(request.placement_id());
            if(getInvetory.isPresent()){
                ChickInventory inventory = getInvetory.get();
                // update quantity in block
                int block_update = chickInventoryRepository.updateBockQuantity(request.placement_id(),request.mortality_quantity(),inventory.getChickStorage().getChick_storage_id());
                if(block_update > 0) {
                    return getInvetory.get().getAvailable_quantity();
                }
            }
            return null;
        }
        return null;
    }

    public String createBlock(BlockRequest request) {
        // get placement id  using code  ---> placement service
        Integer placement_id = this.farmClient.findPlacementIdByCode(request.placement_code());

        //save the record in block entity
        var block = blockRepository.save(mapper.toBlock(request,placement_id, storageRepository));

        return ("Successfully created chick inventory block::" + request.placement_code());
    }

    public List<InvetoryResponse> getAllInventory() {
        return chickInventoryRepository.findAll()
                .stream()
                .map(mapper::fromAllInventory)
                .collect(Collectors.toList());
    }

    public List<FarmUiResponse> forFarmDetails() {
        return chickInventoryRepository.findAll()
                .stream()
                .map(mapper::forFarmUi)
                .collect(Collectors.toList());
    }

    /**
     * |1| get common attribute  --> this is done in previous in getAllInventory() --> filter one detail
     * |2| get block details by using inventory id --> want to farm service
     * - get placement code base on placement id and create list including both.
     * - get mortality count latest and include block list
     */
    public List<BlockDetails> getChickInventoryDetails(Integer chick_inventory_id) {
        // define return array
        List<BlockDetails> response = new ArrayList<>();

        // get storage id
        Integer storageId = chickInventoryRepository.findStorageIdById(chick_inventory_id);
        // get storage object by using storage id
        ChickStorage storageObj = storageRepository.findById(storageId)
                        .orElseThrow(() -> new RuntimeException("Storage with id " + storageId + " not found"));

        for(ChickBlock chickBlock : storageObj.getChickBlocks()) {
            Integer id = chickBlock.getPlacement_id();
            try {
                // get placement code
                String placementCode = farmClient.findPlacementCodeById(id);
                // get mortality details
                ToChickBlockDetails mortality = farmClient.getMortalityDetails(id);
                // add all details to array
                response.add(mapper.toBlockDetails(chickBlock, placementCode, mortality));
            } catch (Exception e) {
                // Log the exception and continue with the next iteration
                logger.warn("Failed to get details for placement_id {}: {}", chickBlock.getPlacement_id(), e.getMessage());
            }
        }
        return response;
    }

    // Send the Inventory id to the monitoring service
    public Integer getInventoryIdByPlacementId(Integer placementId) {
        return chickInventoryRepository.findLatestChickInventoryIdByPlacementId(placementId);
    }

}

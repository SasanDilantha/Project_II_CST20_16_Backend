package com.pms.chick_service.services;

import com.pms.chick_service.client.ExpenseClient;
import com.pms.chick_service.client.PlacementClient;
import com.pms.chick_service.dto.*;
import com.pms.chick_service.dto.ui.response.FarmUiResponse;
import com.pms.chick_service.model.ChickInventory;
import com.pms.chick_service.repository.ChickBlockRepository;
import com.pms.chick_service.repository.ChickInventoryRepository;
import com.pms.chick_service.repository.ChickInventryCostRepository;
import com.pms.chick_service.repository.ChickStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChickInventoryService {
    private final ChickInventoryRepository chickInventoryRepository;
    private final ChickInventoryMapper mapper;
    private final ChickStorageService storageService;
    private final ChickStorageRepository storageRepository;
    private final ChickInventryCostService costService;
    private final ChickInventryCostRepository costRepository;
    private final ChickBlockRepository blockRepository;
    private final ExpenseClient expenseClient;
    private final PlacementClient farmClient;

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

    public List<InvetoryResponse> getAllInvetory() {
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
}

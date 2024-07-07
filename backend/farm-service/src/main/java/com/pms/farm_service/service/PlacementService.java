package com.pms.farm_service.service;

import com.pms.farm_service.dto.GetPlacementResponse;
import com.pms.farm_service.dto.PlacementRequest;
import com.pms.farm_service.dto.PlacementResponse;
import com.pms.farm_service.genarated.TableCode;
import com.pms.farm_service.model.Placement;
import com.pms.farm_service.repository.FarmRepository;
import com.pms.farm_service.repository.PlacementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlacementService {
    private final PlacementRepository placementRepository;
    private final PlacementMapper placementMapper;
    private final FarmService farmService;
    private final FarmRepository farmRepository;
    private final TableCode tableCode;

    /**
     *  Process of create placement:
     *  |*| get the location of farm from farm table
     *  |*| create for loop using number of placement
     *  |*| save placement code and farm id to table
     *  |*| collect save list and return list
     */
    public List<PlacementResponse> createPlacement(PlacementRequest request) {
        List<PlacementResponse> placementResponses = new ArrayList<>();

        String farmLocation = farmService.getFarmLocationById(request.farm_id());
        tableCode.setFarmLocation(farmLocation);
        String placement_code = tableCode.createPlacementCode();
        for (int i = 0; i < request.number_of_placement(); i++) {
            int j = i + 1; // Increment j by 1
            String placementCode_id = placement_code + "/" + j;
            var placement = placementRepository.save(placementMapper.toPlacement(request,placementCode_id, farmRepository));
            placementResponses.add(placementMapper.toPlacementResponce(placement));
        }
        return placementResponses;
    }

    // get placement id by using placement code
    public Integer getPlacementIdByPlacementCode(String placement_code) {
        return placementRepository.findIdByCode(placement_code);
    }

    public List<GetPlacementResponse> getPlacements() {
        return placementRepository.findAll()
                .stream()
                .map(placementMapper::fromAllPlacement)
                .collect(Collectors.toList());
    }
}

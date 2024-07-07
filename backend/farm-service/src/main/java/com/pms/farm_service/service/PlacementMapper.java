package com.pms.farm_service.service;

import com.pms.farm_service.dto.GetPlacementResponse;
import com.pms.farm_service.dto.PlacementRequest;
import com.pms.farm_service.dto.PlacementResponse;
import com.pms.farm_service.genarated.RequestCode;
import com.pms.farm_service.model.Farm;
import com.pms.farm_service.model.Placement;
import com.pms.farm_service.repository.FarmRepository;
import org.springframework.stereotype.Service;

@Service
public class PlacementMapper {
    public RequestCode fromFamCodeGen(Placement placement) {
        return new RequestCode(placement.getPlacement_code());
    }

    public Placement toPlacement(PlacementRequest request, String placementCode, FarmRepository farmRepository) {
        Farm farm =farmRepository.findById(request.farm_id())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.farm_id()));
        return Placement.builder()
                .placement_code(placementCode)
                .farm(farm)
                .build();
    }

    public PlacementResponse toPlacementResponce(Placement placement) {
        return new PlacementResponse(
                placement.getPlacement_id(),
                placement.getPlacement_code()
        );
    }

    public GetPlacementResponse fromAllPlacement(Placement placement) {
        return new GetPlacementResponse(
                placement.getPlacement_id(),
                placement.getPlacement_code(),
                placement.getFarm().getFarm_id()
        );
    }
}

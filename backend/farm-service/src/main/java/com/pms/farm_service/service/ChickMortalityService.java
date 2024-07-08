package com.pms.farm_service.service;

import com.pms.farm_service.clients.ChickClient;
import com.pms.farm_service.dto.ChickMortalityRequest;
import com.pms.farm_service.dto.ui.response.ToChickBlockDetails;
import com.pms.farm_service.repository.ChickMortalityRepository;
import com.pms.farm_service.repository.PlacementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChickMortalityService {
    private final ChickMortalityRepository chickMortalityRepository;
    private final ChickMortalityMapper mapper;
    private final PlacementService placementService;
    private final PlacementRepository repository;
    private final ChickClient chickClient;

    public String addChickMortality(ChickMortalityRequest request) {
        Integer placement_id = placementService.getPlacementIdByPlacementCode(request.placement_code());
        var mortality = chickMortalityRepository.save(mapper.toMortality(request, placement_id, repository));
        // out put the updated a.q
        Integer updateInventory = this.chickClient.updateMortality(mapper.toInventory(mortality));
        return ("Successfully updated chicken mortality in Placement:: " + request.placement_code() + ", and New Available in Inventory:: " + updateInventory);
    }

    public ToChickBlockDetails getMortalityDetails(Integer placementId) {
        var mortality = chickMortalityRepository.findByPlacementId(placementId);
        return mapper.toChick(mortality);
    }
}

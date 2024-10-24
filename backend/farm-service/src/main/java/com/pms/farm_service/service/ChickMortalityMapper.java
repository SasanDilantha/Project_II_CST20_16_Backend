package com.pms.farm_service.service;

import com.pms.farm_service.dto.ChickMortalityRequest;
import com.pms.farm_service.dto.MortalityRequest;
import com.pms.farm_service.dto.ui.response.ToChickBlockDetails;
import com.pms.farm_service.model.ChickMortality;
import com.pms.farm_service.model.Placement;
import com.pms.farm_service.repository.PlacementRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ChickMortalityMapper {
    public ChickMortality toMortality(ChickMortalityRequest request, Integer placement_id, PlacementRepository repository) {
        Placement placement = repository.findById(placement_id)
                .orElseThrow(() -> new RuntimeException("Placement with id " + placement_id + " not found"));
        return ChickMortality.builder()
                .mortality_id(request.mortality_id())
                .quantity(request.mortality_quantity())
                .placement(placement)
                .description(request.description())
                .build();
    }

    public MortalityRequest toInventory(ChickMortality mortality) {
        return new MortalityRequest(
                mortality.getQuantity(),
                mortality.getPlacement().getPlacement_id()
        );
    }

    public ToChickBlockDetails toChick(ChickMortality mortality) {
        Integer mortality_count = (mortality.getQuantity() == null) ? 0 : mortality.getQuantity();
        String mortality_description = (mortality.getDescription() == null) ? "" : mortality.getDescription();
        LocalDate mortality_date = (mortality.getMortality_date() == null) ? LocalDate.now() : mortality.getMortality_date();
        return new ToChickBlockDetails(
                mortality_count,
                mortality_description,
                mortality_date
        );
    }
}

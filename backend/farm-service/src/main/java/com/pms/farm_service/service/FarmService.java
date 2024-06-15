package com.pms.farm_service.service;

import com.pms.farm_service.dto.FarmRequest;
import com.pms.farm_service.dto.FarmResponse;
import com.pms.farm_service.genarated.TableCode;
import com.pms.farm_service.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;
    private final FarmMapper mapper;
    private final TableCode tableCode;

    public String addFarm(FarmRequest request) {
        tableCode.setFarmLocation(request.farm_location());
        String farm_code = tableCode.createFarmCode();
        var farmObj = farmRepository.save(mapper.toFarm(request,farm_code));
        return farmObj.getFarm_code();
    }

    public List<FarmResponse> getAllFarms() {
        return farmRepository.findAll()
                .stream()
                .map(mapper::fromFarm)
                .collect(Collectors.toList());
    }

    public Integer getFarmIdByFarmCode(String farmCode) {
        return farmRepository.findIdByFarmCode(farmCode);
    }

    public String getFarmCodeByFarmId(Integer farmId) {
        return farmRepository.findFarmCodeById(farmId);
    }

    public String getFarmLocationById(Integer farm_id) {
        return farmRepository.findFramLocationByID(farm_id);
    }
}

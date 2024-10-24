package com.pms.farm_service.service;

import com.pms.farm_service.clients.ChickClient;
import com.pms.farm_service.dto.FarmRequest;
import com.pms.farm_service.dto.FarmResponse;
import com.pms.farm_service.dto.ToUserDetails;
import com.pms.farm_service.dto.ui.clients.FarmUiResponse;
import com.pms.farm_service.dto.ui.response.FarmDetailsUiResponse;
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
    private final ChickClient chickClient;

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

    // get all farm details for Farm Details UI
    public List<FarmDetailsUiResponse> getAllFarmDetails() {
        List<FarmResponse> farm_list = this.getAllFarms(); // get farm name | location
        List<FarmUiResponse> from_chick_list = this.chickClient.forFarmDetails();
        return mapper.mapFarmUiData(farm_list,from_chick_list);
    }

    public List<ToUserDetails> getFarmNameAndCodeWithId() {
        List<FarmResponse> farm_list = this.getAllFarms(); // get all farm details and after map it
        return mapper.toUserDetails(farm_list);
    }

    public String getFarmNameById(Integer farmId) {
        return farmRepository.findById(farmId)
                .map(farm -> farm.getFarm_name())
                .orElseThrow(() -> new RuntimeException("Farm not found with id: " + farmId));
    }

}

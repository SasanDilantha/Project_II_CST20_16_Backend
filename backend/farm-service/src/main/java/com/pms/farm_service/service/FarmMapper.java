package com.pms.farm_service.service;

import com.pms.farm_service.dto.FarmRequest;
import com.pms.farm_service.dto.FarmRequestCode;
import com.pms.farm_service.dto.FarmResponse;
import com.pms.farm_service.genarated.RequestCode;
import com.pms.farm_service.model.Farm;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class FarmMapper {
    public Farm toFarm(FarmRequest request, String setFarmcode) {
        return Farm.builder()
                .farm_id(request.farm_id())
                .farm_name(request.farm_name())
                .farm_code(setFarmcode)
                .farm_location(request.farm_location())
                .build();
    }

    public FarmRequestCode fromFamCode(Farm farm) {
        return new FarmRequestCode(farm.getFarm_code());
    }
    public RequestCode fromFamCodeGen(Farm farm) {
        return new RequestCode(farm.getFarm_code());
    }

    public FarmResponse fromFarm(Farm farm) {
        return new FarmResponse(
                farm.getFarm_id(),
                farm.getFarm_name(),
                farm.getFarm_location(),
                farm.getFarm_code()
        );
    }
}

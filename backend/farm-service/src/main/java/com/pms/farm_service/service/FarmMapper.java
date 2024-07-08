package com.pms.farm_service.service;

import com.pms.farm_service.dto.FarmRequest;
import com.pms.farm_service.dto.FarmRequestCode;
import com.pms.farm_service.dto.FarmResponse;
import com.pms.farm_service.dto.ToUserDetails;
import com.pms.farm_service.dto.ui.clients.FarmUiResponse;
import com.pms.farm_service.dto.ui.response.FarmDetailsUiResponse;
import com.pms.farm_service.genarated.RequestCode;
import com.pms.farm_service.model.Farm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<FarmDetailsUiResponse> mapFarmUiData(List<FarmResponse> farmList, List<FarmUiResponse> fromChickList) {
        List<FarmDetailsUiResponse> farmDetailsUiResponseList = new ArrayList<>();
        int size = Math.min(farmList.size(), fromChickList.size());
        for (int i = 0; i < size; i++) {
            FarmResponse farmResponse = farmList.get(i);
            FarmUiResponse fromChick = fromChickList.get(i);
            farmDetailsUiResponseList.add(
                    new FarmDetailsUiResponse(
                            farmResponse.farm_id(),
                            farmResponse.farm_name(),
                            farmResponse.farm_location(),
                            fromChick.inventory_id(),
                            fromChick.inventory_count(),
                            fromChick.available_inventory_count(),
                            fromChick.chick_age()
                    )
            );
        }
        return farmDetailsUiResponseList;
    }

    public List<ToUserDetails> toUserDetails(List<FarmResponse> farmList) {
        List<ToUserDetails> userDeailsList = new ArrayList<>();
        for (FarmResponse farmResponse : farmList) {
            userDeailsList.add(new ToUserDetails(
                    farmResponse.farm_id(),
                    farmResponse.farm_name(),
                    farmResponse.farm_code()
            ));
        }
        return userDeailsList;
    }
}

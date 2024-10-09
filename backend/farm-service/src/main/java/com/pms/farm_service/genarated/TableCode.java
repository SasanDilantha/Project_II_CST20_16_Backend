package com.pms.farm_service.genarated;

import com.pms.farm_service.repository.FarmRepository;
import com.pms.farm_service.repository.PlacementRepository;
import com.pms.farm_service.service.FarmMapper;
import com.pms.farm_service.service.PlacementMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Setter
public class TableCode {
    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;
    private final PlacementRepository placementRepository;
    private final PlacementMapper placementMapper;
    private String farmLocation;

    // create farm code generator
    public String createFarmCode() {
        List<RequestCode> farmRequestCodes = farmRepository.findAll().stream().map(farmMapper::fromFamCodeGen).toList();
        String stringPart = this.farmLocation.substring(0,3);
        return createCode(farmRequestCodes.stream().toList(),stringPart, "FARM");
    }

    // create placement code
    public String createPlacementCode() {
        List<RequestCode> farmRequestCodes = placementRepository.findAll().stream().map(placementMapper::fromFamCodeGen).toList();
        String stringPart = this.farmLocation.substring(0,3);
        return createCode(farmRequestCodes.stream().toList(),stringPart, "PLACE");
    }

    // create record code
    public String createReportCode(){
        int numericPart = this.getRanNum();
        return ("REC-"+numericPart);
    }

    private String createCode(List<RequestCode> list, String stringPart, String tabl) {
        int numericPart = this.getRanNum();
        // farmcode
        String farmCode = stringPart.toUpperCase() +"-" + numericPart;

        // placement code
        String placementCode = stringPart.toUpperCase() +"-PL-" + numericPart;

        String finalCode = tabl.equals("FARM") ? farmCode: placementCode;
        for(RequestCode requestCode : list) {
            if(requestCode.table_code().equals(finalCode)) {
                createCode(list,stringPart,tabl);
            }
        }
        return finalCode;
    }

    private int getRanNum(){
        Random random = new Random();
        int max = 10000, min = 0;
        return (random.nextInt(max - min) + min);
    }

}

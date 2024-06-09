package com.pms.farm_service.service;

import com.pms.farm_service.dto.FarmRequest;
import com.pms.farm_service.dto.FarmRequestCode;
import com.pms.farm_service.dto.FarmResponse;
import com.pms.farm_service.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmService {
    private final FarmRepository farmRepository;
    private final FarmMapper mapper;

    public String addFarm(FarmRequest request) {
        String setFarmcode = createFarmCode(request.farm_location());
        var farmObj = farmRepository.save(mapper.toFarm(request,setFarmcode));
        return farmObj.getFarm_code();
    }


    // generate the farm_code
    // set up the variable for generate farm code
    private String createFarmCode(String location) {
        List<FarmRequestCode> farmRequestCodes = farmRepository.findAll().stream().map(mapper::fromFamCode).toList();
        String stringPart = location.substring(0,3);
        return setCode(farmRequestCodes,stringPart);
    }

    // create farm code and validate it is unique
    private String setCode(List<FarmRequestCode> farmRequestCodes, String stringPart) {
        Random random = new Random();
        int max = 10000, min = 0;
        int numericPart = random.nextInt(max - min) + min;
        String finalCode = stringPart.toUpperCase() +"-" + numericPart;
        for(FarmRequestCode farmRequestCode : farmRequestCodes) {
            if (farmRequestCode.farm_code().equals(finalCode)){
                setCode(farmRequestCodes,stringPart);
            }
        }
        return finalCode;
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
}

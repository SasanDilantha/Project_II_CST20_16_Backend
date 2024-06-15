package com.pms.finace_service.services;

import com.pms.finace_service.clients.FarmClient;
import com.pms.finace_service.dto.ExpenseRequest;
import com.pms.finace_service.repository.ExpensesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpensesService {
    private final ExpensesRepository expensesRepository;
    private final ExpensesMapper mapper;
    private final FarmClient farmClient;


    public Integer createChickInventory(ExpenseRequest request) {
        String farm_code = String.valueOf(farmClient.getFarmCodeByFarmId(request.farm_id()));
        var chickInventory = expensesRepository.save(mapper.toCheckInventry(request, farm_code));
        return chickInventory.getExpense_id();
    }
}

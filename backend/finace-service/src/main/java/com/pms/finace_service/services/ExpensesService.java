package com.pms.finace_service.services;

import com.pms.finace_service.clients.FarmClient;
import com.pms.finace_service.dto.ExpenseRequest;
import com.pms.finace_service.dto.client.ToUserSalary;
import com.pms.finace_service.dto.client.request.ExpenseUserSalaryRequest;
import com.pms.finace_service.repository.ExpensesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpensesService {
    private final ExpensesRepository expensesRepository;
    private final ExpensesMapper mapper;
    private final FarmClient farmClient;


    public Integer createChickInventory(ExpenseRequest request) {
        String farm_code = String.valueOf(farmClient.getFarmCodeByFarmId(request.farm_id()));
        var chickInventory = expensesRepository.save(mapper.toCheckInventory(request, farm_code));
        return chickInventory.getExpense_id();
    }

    public Integer createFeedInventory(ExpenseRequest request) {
        String farm_code = String.valueOf(farmClient.getFarmCodeByFarmId(request.farm_id()));
        var feedInventory = expensesRepository.save(mapper.toFeedInventory(request, farm_code));
        return feedInventory.getExpense_id();
    }

    public Integer createMeditationInventory(ExpenseRequest request) {
        String farm_code = String.valueOf(farmClient.getFarmCodeByFarmId(request.farm_id()));
        var meditationInventory = expensesRepository.save(mapper.toMeditationInventory(request, farm_code));
        return meditationInventory.getExpense_id();
    }

    public Integer createSalary(ExpenseUserSalaryRequest request) {
        var salary = expensesRepository.save(mapper.toExpenseFromUser(request));
        return salary.getExpense_id();
    }

    public List<ToUserSalary> getAllSalary() {
        return expensesRepository.findAllByType()
                .stream()
                .map(mapper::toUserSalary)
                .collect(Collectors.toList());
    }
}

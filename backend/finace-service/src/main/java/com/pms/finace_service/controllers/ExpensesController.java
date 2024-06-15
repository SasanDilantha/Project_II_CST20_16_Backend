package com.pms.finace_service.controllers;

import com.pms.finace_service.dto.ExpenseRequest;
import com.pms.finace_service.services.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpensesController {
    private final ExpensesService service;

    @PostMapping("/chick_inventory")
    public Integer createChickInventory(@RequestBody ExpenseRequest request){
        return service.createChickInventory(request);
    }
    @GetMapping("/chick_inventory")
    public ResponseEntity<String> chechPath(){
        return ResponseEntity.ok("Expense Chicken Inventory");
    }
}

package com.pms.finace_service.controllers;

import com.pms.finace_service.dto.ExpenseRequest;
import com.pms.finace_service.dto.client.ToExpenseInventory;
import com.pms.finace_service.dto.client.ToUserSalary;
import com.pms.finace_service.dto.client.request.ExpenseUserSalaryRequest;
import com.pms.finace_service.services.ExpensesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fin/expenses")
@RequiredArgsConstructor
public class ExpensesController {
    private final ExpensesService service;

    @GetMapping("/")
    public ResponseEntity<String> testPath(){
        return ResponseEntity.ok("<h1>Expense Service<h1>");
    }

    // for create chick inventory details
    @PostMapping("/chick_inventory")
    public Integer createChickInventory(@RequestBody ExpenseRequest request){
        return service.createChickInventory(request);
    }

    // for create feed inventory details
    @PostMapping("/feed_inventory")
    public Integer createFeedInventory(@RequestBody ExpenseRequest request){
        return service.createFeedInventory(request);
    }

    // for create meditation inventory details
    @PostMapping("/meditation_inventory")
    public Integer meditationInventory(@RequestBody ExpenseRequest request){
        return service.createMeditationInventory(request);
    }

    // for create Salary details base on user table
    @PostMapping("/user/salary")
    public Integer createSalary(@RequestBody ExpenseUserSalaryRequest request){
        return service.createSalary(request);
    }

    // get all expense list
    @GetMapping("/get/all/salary")
    public List<ToUserSalary> getAllSalary(){
        return service.getAllSalary();
    }



    @GetMapping("/chick_inventory")
    public ResponseEntity<String> checkPath(){
        return ResponseEntity.ok("Expense Chicken Inventory");
    }

    @GetMapping("/chick_inventory/{expenseId}")
    public ToExpenseInventory getExpenses(@PathVariable("expenseId") Integer expenseId){
        return service.getExpenseInventoryById(expenseId);
    }
}

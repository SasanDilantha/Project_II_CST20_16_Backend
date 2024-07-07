package com.pms.chick_service.client;


import com.pms.chick_service.dto.ExpenseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "finace-service",
        url = "http://localhost:8222/api/expenses"
)
public interface ExpenseClient {
    @PostMapping("/chick_inventory")
    Integer createChickInventory(@RequestBody ExpenseRequest request);
}

package com.pms.medication_service.client;


import com.pms.medication_service.dto.client.ExpenseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "finace-service",
        url = "http://localhost:8222/api/fin/expenses"
)
public interface ExpenseClient {
    @PostMapping("/meditation_inventory")
    Integer meditationInventory(@RequestBody ExpenseRequest request);
}

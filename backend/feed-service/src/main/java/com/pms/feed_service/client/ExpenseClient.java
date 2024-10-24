package com.pms.feed_service.client;


import com.pms.feed_service.dto.client.ExpenseRequest;
import com.pms.feed_service.dto.client.ToExpenseInventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "finace-service",
        url = "http://localhost:8222/api/fin/expenses"
)
public interface ExpenseClient {
    @PostMapping("/feed_inventory")
    Integer createFeedInventory(@RequestBody ExpenseRequest request);

    @GetMapping("/chick_inventory/{expenseId}")
    ToExpenseInventory getExpenses(@PathVariable("expenseId") Integer expenseId);
}

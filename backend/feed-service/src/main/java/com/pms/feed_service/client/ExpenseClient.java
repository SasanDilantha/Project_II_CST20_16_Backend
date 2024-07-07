package com.pms.feed_service.client;


import com.pms.feed_service.dto.client.ExpenseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "finace-service",
        url = "http://localhost:8222/api/expenses"
)
public interface ExpenseClient {
    @PostMapping("/feed_inventory")
    Integer createFeedInventory(@RequestBody ExpenseRequest request);
}

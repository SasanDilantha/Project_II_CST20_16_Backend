package com.pms.user_server.clients;

import com.pms.user_server.dto.client.ExpenseUserSalaryRequest;
import com.pms.user_server.dto.client.ToUserDetails;
import com.pms.user_server.dto.client.ToUserSalary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "finace-service",
        url = "http://localhost:8222/api/fin/expenses",
        configuration = FeignConfig.class   // Ensure FeignConfig is used here
)
public interface FinanceClient {
    @PostMapping("/user/salary")
    Integer createSalary(@RequestBody ExpenseUserSalaryRequest request);

    @GetMapping("/get/all/salary")
    List<ToUserSalary> getAllSalary();
}

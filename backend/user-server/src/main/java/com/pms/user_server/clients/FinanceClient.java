package com.pms.user_server.clients;

import com.pms.user_server.dto.client.ExpenseUserSalaryRequest;
import com.pms.user_server.dto.client.ToUserSalary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "finace-service",
        url = "http://localhost:8222/api/fin/expenses"
)
public interface FinanceClient {
    @PostMapping("/user/salary")
    Integer createSalary(@RequestBody ExpenseUserSalaryRequest request);

    @GetMapping("/get/all/salary")
    List<ToUserSalary> getAllSalary();
}

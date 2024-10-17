package com.pms.user_server.clients;

import com.pms.user_server.dto.client.ToUserDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "farm-service",
        url = "http://localhost:8222/api/farm",
        configuration = FeignConfig.class   // Ensure FeignConfig is used here
)
public interface FarmClient {

    @GetMapping(value = "/code/{farm-code}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> getFarmIdbyFramCode(@PathVariable("farm-code") String farmCode);

    @GetMapping("/user/details")
    List<ToUserDetails> getFarmNameAndCodeWithId();
}

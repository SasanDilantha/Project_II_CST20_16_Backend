package com.pms.user_server.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.*;

@FeignClient(
        name = "farm-service",
        url = "http://localhost:8222/api/farm"
)
public interface FarmClient {
    @GetMapping(value = "/{farm-code}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> getFarmIdbyFramCode(@PathVariable("farm-code") String farmCode);
}

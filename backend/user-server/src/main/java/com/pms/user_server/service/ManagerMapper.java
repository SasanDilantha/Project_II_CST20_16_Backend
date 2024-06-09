package com.pms.user_server.service;

import com.pms.user_server.model.Manager;
import com.pms.user_server.model.User;
import com.pms.user_server.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ManagerMapper {
    public Manager toManager(Integer userId, Integer farmId, UserRepository userRepository) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        return Manager.builder()
                .farm_id(farmId)
                .user(user)
                .build();
    }
}

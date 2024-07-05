package com.pms.user_server.service;

import com.pms.user_server.dto.ui.UserUpadeRequest;
import com.pms.user_server.model.Manager;
import com.pms.user_server.model.User;
import com.pms.user_server.repository.ManagerRepository;
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

    public Manager toUpdateManager(UserUpadeRequest userRequest, Integer farmId, ManagerRepository managerRepository) {
        Manager manager = managerRepository.findById(userRequest.user_id())
                .orElseThrow(
                        () -> new IllegalArgumentException("User not found with id: " + userRequest.user_id())
                );
        manager.setFarm_id(farmId);
        return manager;
    }
}

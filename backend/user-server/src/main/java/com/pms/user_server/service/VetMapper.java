package com.pms.user_server.service;

import com.pms.user_server.model.User;
import com.pms.user_server.model.Veterinarian;
import com.pms.user_server.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class VetMapper {
    public Veterinarian toVet(Integer userId, Integer farmId, UserRepository userRepository) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        return Veterinarian.builder()
                .farm_id(farmId)
                .user(user)
                .build();
    }
}

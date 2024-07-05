package com.pms.user_server.service;

import com.pms.user_server.dto.ui.UserUpadeRequest;
import com.pms.user_server.model.User;
import com.pms.user_server.model.Veterinarian;
import com.pms.user_server.repository.UserRepository;
import com.pms.user_server.repository.VeterinarianRepository;
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

    public Veterinarian toUpdteVet(UserUpadeRequest userRequest, Integer farmId, VeterinarianRepository veterinarianRepository) {
        Veterinarian vet = veterinarianRepository.findById(userRequest.user_id())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userRequest.user_id()));
        vet.setFarm_id(farmId);
        return vet;
    }
}

package com.pms.user_server.service;

import com.pms.user_server.dto.UserRequest;
import com.pms.user_server.dto.UserResponse;
import com.pms.user_server.model.Manager;
import com.pms.user_server.model.User;
import com.pms.user_server.model.Veterinarian;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public User toUser(UserRequest request) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .user_id(request.user_id())
                .first_name(request.first_name())
                .last_name(request.last_name())
                .email(request.email())
                .phone(request.phone())
                .password(request.password())
                .role(request.role())
                .build();
    }

    public UserResponse fromUsers(User user) {
        String userId = String.valueOf(user.getUser_id());
        String firstName = user.getFirst_name();
        String lastName = user.getLast_name();
        String email = user.getEmail();
        String phone = user.getPhone();
        String password = user.getPassword();
        String role = user.getRole().toString();
        String managerFarmId = String.valueOf(user.getManager() != null ? user.getManager().getFarm_id() : null);
        String veterinarianFarmId = String.valueOf(user.getVeterinarian() != null ? user.getVeterinarian().getFarm_id() : null);

        return new UserResponse(
                userId,
                firstName,
                lastName,
                email,
                phone,
                password,
                role,
                managerFarmId,
                veterinarianFarmId);
    }

}

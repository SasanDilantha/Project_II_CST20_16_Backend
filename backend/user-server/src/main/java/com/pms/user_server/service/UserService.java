package com.pms.user_server.service;

import com.pms.user_server.dto.UserLoginRequest;
import com.pms.user_server.dto.UserRequest;
import com.pms.user_server.dto.UserResponse;
import com.pms.user_server.exceptions.UserNotFoundException;
import com.pms.user_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    // privet final FarmClient farmClient;

    public String addUser(UserRequest request) {
        /**
         *      Step of the addUser method logic
         *      ===================================
         *      - check the user
         *      - If role is manager, then get farm id using farm code  ---> farm-service (openFeign)
         *      - If role is veterinarian, then get farm id using farm code  ---> farm-service (openFeign)
         */

        String returnMessage;

        // check user role
        if(request.role().equals("mgr")) {
            Integer farmId = 0; // this is temp value
            var user =  userRepository.save(mapper.toUserManager(request, farmId));
            returnMessage = "New Manager successfully added! with ID::"+user.getUser_id();
        } else if (request.role().equals("vet")) {
            Integer farmId = 0; // this is temp value
            var user =  userRepository.save(mapper.toUserVet(request, farmId));
            returnMessage = "New Veterinarian successfully added! With ID::"+user.getUser_id();
        }else {
            var user =  userRepository.save(mapper.toUser(request));
            returnMessage = "New Admin successfully added! With ID::"+user.getUser_id();
        }
        return returnMessage;
    }

    // user is valid or not
    public Boolean isValidUser(UserLoginRequest request) {
        var user = userRepository.findByEmail(request.email());
        if(user == null) {
            return false;
        }
        return request.password().equals(user.getPassword());
    }

    // get all users
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(mapper::fromUsers)
                .collect(Collectors.toList());
    }

    // get user record
    public UserResponse getUserByID(Integer userId) {
        return userRepository.findById(userId)
                .map(mapper::fromUsers)
                .orElseThrow(
                        () -> new UserNotFoundException("User Not Found with ID::" + userId)
                );
    }
}

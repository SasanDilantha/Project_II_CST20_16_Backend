package com.pms.user_server.service;

import com.pms.user_server.clients.FarmClient;
import com.pms.user_server.dto.UserLoginRequest;
import com.pms.user_server.dto.UserRequest;
import com.pms.user_server.dto.UserResponse;
import com.pms.user_server.exceptions.UserServiceException;
import com.pms.user_server.model.Manager;
import com.pms.user_server.repository.ManagerRepository;
import com.pms.user_server.repository.UserRepository;
import com.pms.user_server.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final UserMapper mapper;
    private final ManagerMapper mgrMapper;
    private final VetMapper vetMapper;
    private final FarmClient farmClient;

    /**
     *      Step of the addUser method logic:
     *
     *      |-| check the user
     *      |-| If role is manager, then get farm id using farm code  ---> farm-service (openFeign)
     *      |-| If role is veterinarian, then get farm id using farm code  ---> farm-service (openFeign)
     */

    public String addUser(UserRequest request) {
        String returnMessage;
        // mail is already exist
        if(mailIsAlreadyExist(request.email())){
            return "Email already exist!";
        }

        // save data to user table
        var user = userRepository.save(mapper.toUser(request));

        // check user role then save data to mgr table or vet table
        if (request.role().toString().equals("MGR") || request.role().toString().equals("VET")) {
            // get user id using email
            Integer user_id = getUserIDByEmail(request.email());
            // get farm id from farm service
            ResponseEntity<Integer> response = this.farmClient.getFarmIdbyFramCode(request.farm_code());
            Integer farmId = response.getBody();

            if (response.getBody() == null) {
                throw new UserServiceException("Invalid farm_code::");
            }

            if(request.role().toString().equals("MGR")) {
                managerRepository.save(mgrMapper.toManager(user_id, farmId,userRepository));
                returnMessage = "New Manager successfully added! With ID::" + user.getUser_id();
            }else {
                veterinarianRepository.save(vetMapper.toVet(user_id, farmId,userRepository));
                returnMessage = "New Veterinarian successfully added! With ID::" + user.getUser_id();
            }

        } else {
            returnMessage = "New Admin successfully added! With ID::" + user.getUser_id();
        }
        return returnMessage;
    }

    // get user id method
    private Integer getUserIDByEmail(String email) {
        var user = userRepository.findByEmail(email);
        return user.getUser_id();
    }
    // mail is already exists method
    private boolean mailIsAlreadyExist(String email) {
        var mail = userRepository.findByEmail(email);
        if (mail != null) {
            return true;
        }
        return false;
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
                        () -> new UserServiceException("User Not Found with ID::" + userId)
                );
    }
}

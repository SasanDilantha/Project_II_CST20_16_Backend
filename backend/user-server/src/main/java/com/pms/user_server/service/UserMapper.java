package com.pms.user_server.service;

import com.pms.user_server.dto.UserRequest;
import com.pms.user_server.dto.UserResponse;
import com.pms.user_server.dto.UserSalaryRequest;
import com.pms.user_server.dto.client.ExpenseUserSalaryRequest;
import com.pms.user_server.dto.client.ToUserDetails;
import com.pms.user_server.dto.client.ToUserSalary;
import com.pms.user_server.dto.ui.UserUpadeRequest;
import com.pms.user_server.dto.ui.users.ManagerVetDetails;
import com.pms.user_server.dto.ui.users.UserUiResponse;
import com.pms.user_server.exceptions.UserServiceException;
import com.pms.user_server.model.User;
import com.pms.user_server.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Integer userId = user.getUser_id();
        String firstName = user.getFirst_name();
        String lastName = user.getLast_name();
        String email = user.getEmail();
        String phone = user.getPhone();
        String password = user.getPassword();
        String role = user.getRole().toString();
        Integer expense_id = user.getExpense_id();
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
                expense_id,
                managerFarmId,
                veterinarianFarmId);
    }

    public ExpenseUserSalaryRequest toSalary(UserSalaryRequest request) {
        String description = " Monthly Salary";
        return new ExpenseUserSalaryRequest(
                null,
                description,
                request.farm_code(),
                request.salary()
        );
    }

    public List<UserUiResponse> mapToUserUiResponse(List<UserResponse> userTableList, List<ToUserDetails> farmList, List<ToUserSalary> salaryList) {
        // Create a map for farm details
        Map<Integer, ToUserDetails> farmMap = farmList.stream()
                .collect(Collectors.toMap(ToUserDetails::farm_id, farm -> farm));

        // Create a map for salary details
        Map<Integer, ToUserSalary> salaryMap = salaryList.stream()
                .collect(Collectors.toMap(ToUserSalary::expense_id, salary -> salary));

        // Create the UserUiResponse list

        return userTableList.stream()
                .map(user -> {
                    Integer managerFarmId = null;
                    Integer veterinarianFarmId = null;
                    if (user.managerFarmId() != null) {
                        try {
                            managerFarmId = Integer.valueOf(user.managerFarmId());
                        } catch (NumberFormatException e) {
                            // Handle exception or log error
                        }
                    }
                    if (user.veterinarianFarmId() != null) {
                        try {
                            veterinarianFarmId = Integer.valueOf(user.veterinarianFarmId());
                        } catch (NumberFormatException e) {
                            // Handle exception or log error
                        }
                    }

                    ToUserDetails farmDetails = farmMap.getOrDefault(managerFarmId,
                            farmMap.get(veterinarianFarmId));
                    ToUserSalary salaryDetails = salaryMap.get(user.expense_id());

                    String farmName = farmDetails != null ? farmDetails.farm_name() : null;
                    String farmCode = farmDetails != null ? farmDetails.farm_code() : null;
                    String salary = salaryDetails != null ? salaryDetails.expense_value().toString() : null;

                    return new UserUiResponse(
                            user.user_id(),
                            user.first_name(),
                            user.last_name(),
                            user.email(),
                            user.phone(),
                            user.role(),
                            farmName,
                            farmCode,
                            salary
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     *
     * @param isNewMailAlreadyExist
     * @return this is boolean out put. this true mean request mail already exist.
     *  if this true then check the request mail and database mail is same.
     *  if these two are not same it mean update mail is already exist.
     *
     */

    public User updateUser(UserUpadeRequest userRequest, UserRepository userRepository, Boolean isNewMailAlreadyExist) {
        User user = userRepository.findById(userRequest.user_id())
                .orElseThrow(
                        () -> new UserServiceException("User not found with user ID::"  + userRequest.user_id())
                );
        if(isNewMailAlreadyExist) {
            if (user.getEmail().equals(userRequest.email())) {
                user.setEmail(userRequest.email());
            }else {
                throw  new UserServiceException( "Email already exist in system ! With mail::" + userRequest.email());
            }
        }
        user.setFirst_name(userRequest.first_name());
        user.setLast_name(userRequest.last_name());
        user.setPhone(userRequest.phone());
        user.setRole(userRequest.role());
        return user;
    }

    public ManagerVetDetails fromManagerVet(User user) {
        String name = user.getFirst_name() + " " + user.getLast_name();
        return new ManagerVetDetails(
                name,
                user.getEmail(),
                user.getPhone()
        );
    }
}

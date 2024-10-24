package com.pms.user_server.service;

import com.pms.user_server.clients.FarmClient;
import com.pms.user_server.clients.FinanceClient;
import com.pms.user_server.dto.UserLoginRequest;
import com.pms.user_server.dto.UserRequest;
import com.pms.user_server.dto.UserResponse;
import com.pms.user_server.dto.UserSalaryRequest;
import com.pms.user_server.dto.client.ToUserDetails;
import com.pms.user_server.dto.client.ToUserSalary;
import com.pms.user_server.dto.ui.UserUpadeRequest;
import com.pms.user_server.dto.ui.users.ManagerVetDetails;
import com.pms.user_server.dto.ui.users.UserUiResponse;
import com.pms.user_server.exceptions.UserServiceException;
import com.pms.user_server.model.User;
import com.pms.user_server.repository.ManagerRepository;
import com.pms.user_server.repository.UserRepository;
import com.pms.user_server.repository.VeterinarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private final FinanceClient financeClient;

    /**
     *      Step of the addUser method logic:
     *      |1| check the user
     *      |2| If role is manager, then get farm id using farm code  ---> farm-service (openFeign)
     *      |3| If role is veterinarian, then get farm id using farm code  ---> farm-service (openFeign)
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
        return mail != null;
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

    /**
     *
     *  |*| ===> map UserResponse --> UserUiResponse and return it
     *  |1| get All farm name and farm code  with farm id from farm service
     *  |2| get list of expense ids and values where expense type = salary
     *  |3| combine the 3 list
     *      - user table list
     *      - farm list
     *      - salary list
     */
    public List<UserUiResponse>  getAllUsersForUI() {
        List<UserUiResponse> responses = new ArrayList<>();
        List<UserResponse> user_table_list = this.getAllUsers();
        List<ToUserDetails> farm_list = farmClient.getFarmNameAndCodeWithId();
        List<ToUserSalary> salaryList = financeClient.getAllSalary();

        return  mapper.mapToUserUiResponse(user_table_list,farm_list,salaryList);
    }

    /**
     *  |1| Send the salary value and farm code to finance service and get expense id
     *  |2| Update the user table base on user id
     */
    @Transactional
    public Integer updateOrSetSalary(UserSalaryRequest request) {
        // get expense from finance service
        Integer expense_id = financeClient.createSalary(mapper.toSalary(request));
        // update the user table
        if(expense_id != null) {
            int row = userRepository.updateExpenseId(expense_id, request.user_id());
            return (row > 0) ? request.user_id() : null;
        }
        return null;
    }

    public String updateUserDetails(UserUpadeRequest userRequest) {
        Boolean isNewMailAlreadyExist = false;
        // mail is already exist
        if(mailIsAlreadyExist(userRequest.email())){
            isNewMailAlreadyExist = true;
        }
        // user set the value of farm relate things
        if(userRequest.farm_code() !=null){
            // check user role then save data to mgr table or vet table
            if (userRequest.role().toString().equals("MGR") || userRequest.role().toString().equals("VET")) {
                // get farm id from farm service
                ResponseEntity<Integer> response = this.farmClient.getFarmIdbyFramCode(userRequest.farm_code());
                Integer farmId = response.getBody();

                if (response.getBody() == null) {
                    throw new UserServiceException("Invalid farm_code::");
                }

                if(userRequest.role().toString().equals("MGR")) {
                    managerRepository.save(mgrMapper.toUpdateManager(userRequest, farmId,managerRepository));
                }else {
                    veterinarianRepository.save(vetMapper.toUpdteVet(userRequest, farmId,veterinarianRepository));
                }

            }
        }
        var updatedUser = userRepository.save(mapper.updateUser(userRequest, userRepository, isNewMailAlreadyExist));
        return ("Successfully updated User::" + updatedUser.getUser_id());
    }

    /**
     *  |*| ==> get manager details for ui
     *  |1| get manger id by using farm id  in manager table
     *  |2| the get user details by using manager id
     *
     * @param farmId
     * @return farm related manager Details
     *  -   Name , email and phone number
     */
    public ManagerVetDetails getManagerDetails(Integer farmId) {
        var manager = managerRepository.findById(farmId);
        return userRepository.findById(manager.get().getUser_id())
                .map(mapper::fromManagerVet)
                .orElseThrow(
                        () -> new UserServiceException("Manager not fond with Farm ID::" + farmId)
                );
    }

    // get all manager details
    public List<ManagerVetDetails> getAllManagers() {
        return userRepository.findAllManagers()
                .stream()
                .map(mapper::fromManagerVet)
                .collect(Collectors.toList());

    }


    /**
     *  |*| ==> get vet details for ui
     *  |1| get vet id by using farm id  in vet table
     *  |2| the get user details by using manager id
     *
     * @param farmId
     * @return farm related vet Details
     *  -   Name , email and phone number
     */
    public ManagerVetDetails getVetDetails(Integer farmId) {
        var vet = managerRepository.findById(farmId);
        return userRepository.findById(vet.get().getUser_id())
                .map(mapper::fromManagerVet)
                .orElseThrow(
                        () -> new UserServiceException("Vet not fond with Farm ID::" + farmId)
                );
    }

    // get all manager details
    public List<ManagerVetDetails> getAllVets() {
        return userRepository.findAllVets()
                .stream()
                .map(mapper::fromManagerVet)
                .collect(Collectors.toList());

    }

    // count users by farm id
    public Integer countUsersByFarmId(Integer farmId) {
        return userRepository.countUsersByFarmId(farmId);
    }

    // check passwordIsCorrect
    public Boolean passwordIsCorrect(Integer userId, String password) {
        var user = userRepository.findById(userId);
        try {
            if (user.isEmpty()) {
                return false;
            }
            String userPassword = EncryptionUtils.decrypt(user.get().getPassword());
            return password.equals(userPassword);
        } catch (Exception e) {
            return false;
        }
    }

    // Update User
    public User updateUser(Integer userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);

        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            // Update the fields here
            existingUser.setFirst_name(updatedUser.getFirst_name());
            existingUser.setLast_name(updatedUser.getLast_name());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            existingUser.setPassword(updatedUser.getPassword()); // Remember to hash the password if not hashed.
            existingUser.setRole(updatedUser.getRole());
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }

    // Delete User
    public void deleteUser(Integer userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new RuntimeException("User not found with id: " + userId);
        }
    }
}

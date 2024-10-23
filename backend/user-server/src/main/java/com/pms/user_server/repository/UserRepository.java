package com.pms.user_server.repository;

import com.pms.user_server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    @Query("SELECT user FROM User user WHERE user.role = 'MGR'")
    List<User> findAllManagers();

    @Query("SELECT user FROM User user WHERE user.role = 'VET'")
    List<User> findAllVets();

    @Modifying
    @Query("UPDATE User user SET user.expense_id = :expenseId WHERE user.user_id = :userId")
    int updateExpenseId(@Param("expenseId") Integer expenseId, @Param("userId") Integer user_id);
}

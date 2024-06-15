package com.pms.user_server.repository;

import com.pms.user_server.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> { }

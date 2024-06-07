package com.pms.user_server.repository;

import com.pms.user_server.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Integer> { }

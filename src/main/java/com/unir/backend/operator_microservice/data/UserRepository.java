package com.unir.backend.operator_microservice.data;

import com.unir.backend.operator_microservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

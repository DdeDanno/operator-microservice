package com.unir.backend.operator_microservice.data;

import com.unir.backend.operator_microservice.model.Operation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}

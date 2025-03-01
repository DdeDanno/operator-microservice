package com.unir.backend.operator_microservice.service;

import com.unir.backend.operator_microservice.model.DTO.OperationDTO;
import com.unir.backend.operator_microservice.model.Operation;

import java.util.List;

public interface OperatorService {
    List<Operation> getAll();
    Operation newOperation (OperationDTO operationDTO);
}

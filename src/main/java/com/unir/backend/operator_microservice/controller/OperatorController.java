package com.unir.backend.operator_microservice.controller;

import com.unir.backend.operator_microservice.model.DTO.OperationDTO;
import com.unir.backend.operator_microservice.model.Operation;
import com.unir.backend.operator_microservice.service.OperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OperatorController {
    @Autowired
    private OperatorService operatorService;

    @PostMapping("/operation")
    public ResponseEntity<Operation> create(@RequestBody OperationDTO operationDTO) {
        Operation operationCreted = operatorService.newOperation(operationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(operationCreted);
    }

    @GetMapping("/operation")
    public ResponseEntity<List<Operation>> getElasticMovies() {
        List<Operation> response = operatorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

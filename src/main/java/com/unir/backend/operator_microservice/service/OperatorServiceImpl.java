package com.unir.backend.operator_microservice.service;

import com.unir.backend.operator_microservice.data.OperationRepository;
import com.unir.backend.operator_microservice.data.UserRepository;
import com.unir.backend.operator_microservice.model.DTO.MovieDTO;
import com.unir.backend.operator_microservice.model.DTO.OperationDTO;
import com.unir.backend.operator_microservice.model.Operation;
import com.unir.backend.operator_microservice.model.User;
import com.unir.backend.operator_microservice.utils.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OperatorServiceImpl implements OperatorService {
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    private final WebClient.Builder clientBuilder;

    public OperatorServiceImpl(WebClient.Builder clientBuilder, UserRepository userRepository, OperationRepository operationRepository) {
        this.clientBuilder = clientBuilder;
        this.userRepository = userRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public List<Operation> getAll() {
        return operationRepository.findAll();
    }

    @Override
    public Operation newOperation(OperationDTO operationDTO) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(operationDTO.getEmail()));

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setName(operationDTO.getUserName());
            newUser.setEmail(operationDTO.getEmail());
            newUser.setNationality(operationDTO.getNationality());
            newUser.setPhone(operationDTO.getPhone());
            userRepository.save(newUser);
            user = Optional.of(newUser);
        }
        MovieDTO movie = getMovieById(operationDTO.getMovieId()).block();

        Operation operation = new Operation();
        operation.setUser(user.get());
        operation.setOperationDate(LocalDate.now().toString());
        operation.setOperationType(
                "renta".equalsIgnoreCase(operationDTO.getOperationType()) ?
                        OperationType.RENT :
                        OperationType.BUY
        );
        operation.setPrice(operationDTO.getPrice());

        if (movie != null) {
            operation.setMovieId(movie.getId());
        } else {
            throw new RuntimeException("No se encontró la película con ID: " + operationDTO.getMovieId());
        }

        return operationRepository.save(operation);
    }

    public Mono<MovieDTO> getMovieById(String id) {
        return clientBuilder.baseUrl("lb://SEARCH-MICROSERVICE")
                .build()
                .get()
                .uri("/elastic/movies/{id}", id)
                .retrieve()
                .bodyToMono(MovieDTO.class);
    }

}

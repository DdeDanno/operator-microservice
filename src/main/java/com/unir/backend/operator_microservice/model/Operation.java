package com.unir.backend.operator_microservice.model;

import com.unir.backend.operator_microservice.utils.OperationType;
import jakarta.persistence.*;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private String movieId;
    private OperationType operationType;
    private Double price;
    private String operationDate;

    public Operation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", user=" + user +
                ", movieId='" + movieId + '\'' +
                ", operationType=" + operationType +
                ", price=" + price +
                ", operationDate='" + operationDate + '\'' +
                '}';
    }
}

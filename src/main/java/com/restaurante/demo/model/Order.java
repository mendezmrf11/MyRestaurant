package com.restaurante.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String clientDocument;
    private UUID productUuid;
    private int quantity;
    private String extraInformation;
    private LocalDate creationDateTime;
    private double subtotal;
    private double tax;
    private double grandTotal;
    private boolean delivered;
    private LocalDate deliveredDate;
    @Column(unique = true,
            columnDefinition = "BINARY(16)")
    private UUID uuid = UUID.randomUUID();

}

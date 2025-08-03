package com.ticket.inventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    @Id
    private Long id;
    private String name;
    private Long totalCapacity;
    private Long leftCapacity;
    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue; // foreign key
    private BigDecimal ticket_price;
}

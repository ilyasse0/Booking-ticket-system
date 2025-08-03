package com.ticket.inventoryservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigInteger;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venue {

    @Id
    private Long id;
    private String name;
    private Long totalCapacity;
    private String address;
}

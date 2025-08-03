package com.ticket.bookingservice.feign.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VenueInventoryResponse {
    private Long eventId;
    private String eventName;
    private BigDecimal ticketPrice;
    private Integer ticketsAvailable;
}

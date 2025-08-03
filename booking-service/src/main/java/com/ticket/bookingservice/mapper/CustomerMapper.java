package com.ticket.bookingservice.mapper;

import com.ticket.bookingservice.dto.CustomerResponse;
import com.ticket.bookingservice.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }
}

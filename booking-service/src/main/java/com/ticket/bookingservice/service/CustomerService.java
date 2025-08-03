package com.ticket.bookingservice.service;

import com.ticket.bookingservice.dto.CustomerResponse;
import com.ticket.bookingservice.entity.Customer;
import com.ticket.bookingservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer fetchCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer with this id ::"+customerId+"  not found"));
    }
}

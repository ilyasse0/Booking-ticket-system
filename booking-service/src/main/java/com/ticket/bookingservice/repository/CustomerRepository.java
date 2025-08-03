package com.ticket.bookingservice.repository;

import com.ticket.bookingservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends  JpaRepository<Customer, Long> {
}

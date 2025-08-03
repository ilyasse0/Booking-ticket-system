package com.ticket.bookingservice.controller;

import com.ticket.bookingservice.dto.BookingRequest;
import com.ticket.bookingservice.dto.BookingResponse;
import com.ticket.bookingservice.dto.CustomerResponse;
import com.ticket.bookingservice.mapper.CustomerMapper;
import com.ticket.bookingservice.service.BookingService;
import com.ticket.bookingservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class BookingController {

    private final BookingService bookingService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping(consumes = "application/json" , produces = "application/json" , path = "/booking")
    public ResponseEntity<BookingResponse> createBooking(@RequestBody @Valid BookingRequest request){
        log.warn("Booking request from the controller: ----> {}", request.getUserId());
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    @GetMapping("booking/customer/{customer_id}")
    public @ResponseBody CustomerResponse fetchCustomer (@PathVariable Long customer_id) {
        return customerMapper.toCustomerResponse(customerService.fetchCustomerById(customer_id));

    }


}

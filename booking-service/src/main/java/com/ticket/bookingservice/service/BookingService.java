package com.ticket.bookingservice.service;

import com.ticket.bookingservice.dto.BookingRequest;
import com.ticket.bookingservice.dto.BookingResponse;
import com.ticket.bookingservice.dto.InventoryResponse;
import com.ticket.bookingservice.entity.Customer;
//import com.ticket.bookingservice.event.BookingEvent; SWITCH TO BOOKINGEVENT FROM THE COMMON SERVICE
import com.ticket.bookingservice.feign.EventClient;
import com.ticket.commonevents.BookingEvents;
import com.ticket.bookingservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final CustomerRepository customerRepository;
    private final EventClient eventClient;
    private final KafkaTemplate<String, BookingEvents> kafkaTemplate;


    public BookingResponse createBooking(BookingRequest request) {
        // check if the customer exists
        Customer customer = customerRepository.findById(request.getUserId()).orElseThrow(
                () -> new RuntimeException("Customer was not found in our databases")
        );


        // check if there enough inventory
        Long ticketCount = request.getTicketCount();
        Long eventId = request.getEventId();
        //Fetch event inventory using Feign client
        InventoryResponse event = eventClient.inventoryEvent(eventId);
        if (event.getCapacity() < ticketCount) {
            throw new RuntimeException("NOT ENOUGH TICKETS AVAILABLE FOR THIS EVENT !");
        }


        //create Booking
        //BookingResponse bookingEvent = createBookingEvent( request , customer , event);
        BookingEvents bk_event = createBookingEvent(request, customer, event);

        // send booking to order Service on Kafka topic
        kafkaTemplate.send("booking" , bk_event);
        log.info("Booking sent to Kafka: {}", bk_event);




        return BookingResponse.builder()
                .userId(bk_event.getUserId())
                .eventId(bk_event.getEventId())
                .ticketCount(bk_event.getTicketCount())
                .totalPrice(bk_event.getTotalPrice())
                .build();
    }


    private BookingEvents createBookingEvent(BookingRequest request, Customer customer, InventoryResponse event) {
        return BookingEvents
                .builder()
                .userId(customer.getId())
                .eventId(request.getEventId())
                .ticketCount(request.getTicketCount())
                .totalPrice(event.getTicketPrice().multiply(BigDecimal.valueOf(request.getTicketCount())))
                .build();
    }
}

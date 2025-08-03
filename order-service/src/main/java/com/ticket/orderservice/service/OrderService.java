package com.ticket.orderservice.service;

import com.ticket.commonevents.BookingEvents;
import com.ticket.orderservice.entity.Order;
import com.ticket.orderservice.feign.InventoryClient;
import com.ticket.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvents bookingEvent){
        log.info("Booking event received: " + bookingEvent);

        // Create order Obj for the db
        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);
        // Update the inventory
        inventoryClient.updateEventCapacity(order.getEventId() , order.getTicketCount());
    }

    private Order createOrder(BookingEvents bookingEvent) {
        return Order.builder()
                .customerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }
}

package com.ticket.orderservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name ="inventory-service" , url = "${inventory.service.url}")
public interface InventoryClient {


    @PutMapping("inventory/event/{event_id}/capacity/{capacity}")
    public ResponseEntity<Void> updateEventCapacity (
            @PathVariable("event_id") Long event_id,
            @PathVariable("capacity") Long ticketBooked
    );
}

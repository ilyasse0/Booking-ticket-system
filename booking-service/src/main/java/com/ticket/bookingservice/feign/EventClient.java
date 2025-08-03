package com.ticket.bookingservice.feign;
import com.ticket.bookingservice.dto.InventoryResponse;
import com.ticket.bookingservice.feign.dto.VenueInventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service" , url ="${inventory.service.url}")
public interface EventClient {

    @GetMapping("/inventory/event/{eventId}")
    InventoryResponse inventoryEvent(@PathVariable("eventId") Long eventId);
}

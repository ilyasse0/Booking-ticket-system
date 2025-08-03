package com.ticket.inventoryservice.controller;

import com.ticket.inventoryservice.dto.EventInventoryResponse;
import com.ticket.inventoryservice.dto.VenueInventoryResponse;
import com.ticket.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;


    ///  fetching all events
    @GetMapping("/inventory/events")
    public @ResponseBody List<EventInventoryResponse> inventoryGeAllEvents() {
        return inventoryService.getAllEvents();
    }


    @GetMapping("/inventory/event/{eventId}")
    public @ResponseBody EventInventoryResponse inventoryEvent(@PathVariable("eventId") Long eventId) {
        return inventoryService.getEventByEventId(eventId);
    }


    @GetMapping("/inventory/venue/{venueId}")
    public @ResponseBody VenueInventoryResponse inventoryByVenue(
            @PathVariable("venueId") Long venueId
    ) {
        return inventoryService.getVenueInventoryResponse(venueId);
    }


    @PutMapping("inventory/event/{event_id}/capacity/{capacity}")
    public ResponseEntity<Void> updateEventCapacity (
            @PathVariable("event_id") Long event_id,
            @PathVariable("capacity") Long ticketBooked
    ) {
        inventoryService.updateEventCapacity(event_id , ticketBooked);
        return ResponseEntity.ok().build();

    }
}

package com.ticket.inventoryservice.service;

import com.ticket.inventoryservice.dto.EventInventoryResponse;
import com.ticket.inventoryservice.dto.VenueInventoryResponse;
import com.ticket.inventoryservice.entity.Event;
import com.ticket.inventoryservice.entity.Venue;
import com.ticket.inventoryservice.repository.EventRepository;
import com.ticket.inventoryservice.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    public List<EventInventoryResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events
                .stream()
                .map(event -> EventInventoryResponse.builder()
                        .event(event.getName())
                        .capacity(event.getLeftCapacity())
                        .venue(event.getVenue())
                        .build()
                ).collect(Collectors.toList());
    }

    public VenueInventoryResponse getVenueInventoryResponse(Long venueId) {
        Venue venue = venueRepository.findById(venueId).orElse(null);
        return VenueInventoryResponse
                .builder()
                .venueId(venue.getId()) // todo handle this warning
                .venueName(venue.getName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
    }

    public EventInventoryResponse getEventByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        return  EventInventoryResponse
                .builder()
                .eventId(eventId)
                .event(event.getName())
                .capacity(event.getLeftCapacity())
                .ticketPrice(event.getTicket_price())
                .venue(event.getVenue())
                .build();
    }

    public void updateEventCapacity(Long eventId, Long ticketBooked) {

        // fetch the event
        Event eventUpdated = eventRepository.findById(eventId).orElseThrow(
                () -> new IllegalArgumentException("Event with the id" + eventId +" not found")
        );

        // update the capacity
        eventUpdated.setLeftCapacity(eventUpdated.getLeftCapacity() - ticketBooked);
        // persist the data
        eventRepository.saveAndFlush(eventUpdated);
        log.info("Updated event capacity ::: New Capacity --> " + eventUpdated.getLeftCapacity());
    }
}

package com.eventhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.EventDto;
import com.eventhub.entity.Event;
import com.eventhub.entity.Venue;
import com.eventhub.repository.EventRepository;
import com.eventhub.repository.VenueRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VenueRepository venueRepository;

    // Add Event
    public String addEvent(EventDto eventDto) {

        Optional<Venue> venueOptional = venueRepository.findById(eventDto.getVenueId());

        if (venueOptional.isEmpty()) {
            return "Venue Not Found";
        }

        Venue venue = venueOptional.get();

        Event event = new Event();

        event.setEventName(eventDto.getEventName());
        event.setCategory(eventDto.getCategory());
        event.setDescription(eventDto.getDescription());
        event.setVenue(venue);
        event.setEventDate(eventDto.getEventDate());
        event.setEventTime(eventDto.getEventTime());
        event.setDurationHours(eventDto.getDurationHours());
        event.setCapacity(eventDto.getCapacity());
        event.setBasePrice(eventDto.getBasePrice());
        event.setImageUrl(eventDto.getImageUrl());
        event.setStatus(eventDto.getStatus());
        event.setOrganizerName(eventDto.getOrganizerName());
        event.setOrganizerPhone(eventDto.getOrganizerPhone());

        eventRepository.save(event);

        return "Event Added Successfully";
    }

    // Get All Events
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Get Event By Id
    public Event getEventById(Integer id) {

        Optional<Event> optional = eventRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    // Update Event
    public String updateEvent(Integer id, EventDto eventDto) {

        Optional<Event> optional = eventRepository.findById(id);

        if (optional.isEmpty()) {
            return "Event Not Found";
        }

        Optional<Venue> venueOptional = venueRepository.findById(eventDto.getVenueId());

        if (venueOptional.isEmpty()) {
            return "Venue Not Found";
        }

        Event event = optional.get();
        Venue venue = venueOptional.get();

        event.setEventName(eventDto.getEventName());
        event.setCategory(eventDto.getCategory());
        event.setDescription(eventDto.getDescription());
        event.setVenue(venue);
        event.setEventDate(eventDto.getEventDate());
        event.setEventTime(eventDto.getEventTime());
        event.setDurationHours(eventDto.getDurationHours());
        event.setCapacity(eventDto.getCapacity());
        event.setBasePrice(eventDto.getBasePrice());
        event.setImageUrl(eventDto.getImageUrl());
        event.setStatus(eventDto.getStatus());
        event.setOrganizerName(eventDto.getOrganizerName());
        event.setOrganizerPhone(eventDto.getOrganizerPhone());

        eventRepository.save(event);

        return "Event Updated Successfully";
    }

    // Delete Event
    public String deleteEvent(Integer id) {

        if (eventRepository.existsById(id)) {

            eventRepository.deleteById(id);

            return "Event Deleted Successfully";
        }

        return "Event Not Found";
    }

}
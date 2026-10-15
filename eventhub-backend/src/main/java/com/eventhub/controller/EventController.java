package com.eventhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhub.dto.EventDto;
import com.eventhub.entity.Event;
import com.eventhub.service.EventService;

@RestController
@RequestMapping("/events")
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventService eventService;

    // Add Event
    @PostMapping("/add")
    public ResponseEntity<String> addEvent(@RequestBody EventDto eventDto) {

        return new ResponseEntity<>(eventService.addEvent(eventDto), HttpStatus.CREATED);
    }

    // Get All Events
    @GetMapping
    public List<Event> getAllEvents() {

        return eventService.getAllEvents();
    }

    // Get Event By Id
    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Integer id) {

        return eventService.getEventById(id);
    }

    // Update Event
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEvent(@PathVariable Integer id,
                                              @RequestBody EventDto eventDto) {

        return new ResponseEntity<>(eventService.updateEvent(id, eventDto), HttpStatus.OK);
    }

    // Delete Event
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Integer id) {

        return new ResponseEntity<>(eventService.deleteEvent(id), HttpStatus.OK);
    }
}
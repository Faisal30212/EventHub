package com.eventhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhub.dto.VenueDto;
import com.eventhub.entity.Venue;
import com.eventhub.service.VenueService;

@RestController
@RequestMapping("/venues")
@CrossOrigin("*")
public class VenueController {

    @Autowired
    private VenueService venueService;

    // Add Venue
    @PostMapping("/add")
    public ResponseEntity<String> addVenue(@RequestBody VenueDto venueDto) {

        return new ResponseEntity<>(venueService.addVenue(venueDto), HttpStatus.CREATED);
    }

    // Get All Venues
    @GetMapping
    public List<Venue> getAllVenues() {

        return venueService.getAllVenues();
    }

    // Get Venue By Id
    @GetMapping("/{id}")
    public Venue getVenueById(@PathVariable Integer id) {

        return venueService.getVenueById(id);
    }

    // Update Venue
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVenue(@PathVariable Integer id,
        @RequestBody VenueDto venueDto) {

        return new ResponseEntity<>(venueService.updateVenue(id, venueDto), HttpStatus.OK);
    }

    // Delete Venue
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenue(@PathVariable Integer id) {

        return new ResponseEntity<>(venueService.deleteVenue(id), HttpStatus.OK);
    }
}
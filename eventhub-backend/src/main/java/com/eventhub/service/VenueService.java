package com.eventhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.VenueDto;
import com.eventhub.entity.Venue;
import com.eventhub.repository.VenueRepository;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepository;

    // Add Venue
    public String addVenue(VenueDto venueDto) {

        Venue venue = new Venue();

        venue.setVenueName(venueDto.getVenueName());
        venue.setAddress(venueDto.getAddress());
        venue.setCity(venueDto.getCity());
        venue.setCapacity(venueDto.getCapacity());
        venue.setPricePerDay(venueDto.getPricePerDay());
        venue.setAvailability(venueDto.getAvailability());

        venueRepository.save(venue);

        return "Venue Added Successfully";
    }

    // Get All Venues
    public List<Venue> getAllVenues() {
        return venueRepository.findAll();
    }

    // Get Venue By Id
    public Venue getVenueById(Integer id) {

        Optional<Venue> optional = venueRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    // Update Venue
    public String updateVenue(Integer id, VenueDto venueDto) {

        Optional<Venue> optional = venueRepository.findById(id);

        if (optional.isPresent()) {

            Venue venue = optional.get();

            venue.setVenueName(venueDto.getVenueName());
            venue.setAddress(venueDto.getAddress());
            venue.setCity(venueDto.getCity());
            venue.setCapacity(venueDto.getCapacity());
            venue.setPricePerDay(venueDto.getPricePerDay());
            venue.setAvailability(venueDto.getAvailability());

            venueRepository.save(venue);

            return "Venue Updated Successfully";
        }

        return "Venue Not Found";
    }

    // Delete Venue
    public String deleteVenue(Integer id) {

        if (venueRepository.existsById(id)) {

            venueRepository.deleteById(id);

            return "Venue Deleted Successfully";
        }

        return "Venue Not Found";
    }
}
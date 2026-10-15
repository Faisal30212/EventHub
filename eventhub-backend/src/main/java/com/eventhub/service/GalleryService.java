package com.eventhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.GalleryDto;
import com.eventhub.entity.Event;
import com.eventhub.entity.Gallery;
import com.eventhub.repository.EventRepository;
import com.eventhub.repository.GalleryRepository;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    @Autowired
    private EventRepository eventRepository;

    // Add Gallery Image
    public String addGalleryImage(GalleryDto galleryDto) {

        Optional<Event> optional = eventRepository.findById(galleryDto.getEventId());

        if (optional.isEmpty()) {
            return "Event Not Found";
        }

        Event event = optional.get();

        Gallery gallery = new Gallery();

        gallery.setEvent(event);
        gallery.setImageUrl(galleryDto.getImageUrl());
        gallery.setTitle(galleryDto.getTitle());
        gallery.setDescription(galleryDto.getDescription());

        galleryRepository.save(gallery);

        return "Gallery Image Added Successfully";
    }

    // Get All Gallery Images
    public List<Gallery> getAllGalleryImages() {

        return galleryRepository.findAll();
    }

    // Get Gallery Image By Id
    public Gallery getGalleryImageById(Integer id) {

        Optional<Gallery> optional = galleryRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    // Get Gallery Images By Event
    public List<Gallery> getGalleryByEvent(Integer eventId) {

        return galleryRepository.findByEventEventId(eventId);
    }

    // Update Gallery Image
    public String updateGalleryImage(Integer id, GalleryDto galleryDto) {

        Optional<Gallery> optional = galleryRepository.findById(id);

        if (optional.isEmpty()) {
            return "Gallery Image Not Found";
        }

        Optional<Event> eventOptional =
                eventRepository.findById(galleryDto.getEventId());

        if (eventOptional.isEmpty()) {
            return "Event Not Found";
        }

        Gallery gallery = optional.get();

        gallery.setEvent(eventOptional.get());
        gallery.setImageUrl(galleryDto.getImageUrl());
        gallery.setTitle(galleryDto.getTitle());
        gallery.setDescription(galleryDto.getDescription());

        galleryRepository.save(gallery);

        return "Gallery Image Updated Successfully";
    }

    // Delete Gallery Image
    public String deleteGalleryImage(Integer id) {

        if (galleryRepository.existsById(id)) {

            galleryRepository.deleteById(id);

            return "Gallery Image Deleted Successfully";
        }

        return "Gallery Image Not Found";
    }
}
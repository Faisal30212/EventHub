package com.eventhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhub.dto.GalleryDto;
import com.eventhub.entity.Gallery;
import com.eventhub.service.GalleryService;

@RestController
@RequestMapping("/gallery")
@CrossOrigin("*")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    // Add Gallery Image
    @PostMapping("/add")
    public ResponseEntity<String> addGalleryImage(
            @RequestBody GalleryDto galleryDto) {

        return new ResponseEntity<>(
                galleryService.addGalleryImage(galleryDto),
                HttpStatus.CREATED);
    }

    // Get All Gallery Images
    @GetMapping
    public List<Gallery> getAllGalleryImages() {

        return galleryService.getAllGalleryImages();
    }

    // Get Gallery Image By Id
    @GetMapping("/{id}")
    public Gallery getGalleryImageById(@PathVariable Integer id) {

        return galleryService.getGalleryImageById(id);
    }

    // Get Gallery Images By Event
    @GetMapping("/event/{eventId}")
    public List<Gallery> getGalleryByEvent(
            @PathVariable Integer eventId) {

        return galleryService.getGalleryByEvent(eventId);
    }

    // Update Gallery Image
    @PutMapping("/{id}")
    public ResponseEntity<String> updateGalleryImage(
            @PathVariable Integer id,
            @RequestBody GalleryDto galleryDto) {

        return new ResponseEntity<>(
                galleryService.updateGalleryImage(id, galleryDto),
                HttpStatus.OK);
    }

    // Delete Gallery Image
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGalleryImage(
            @PathVariable Integer id) {

        return new ResponseEntity<>(
                galleryService.deleteGalleryImage(id),
                HttpStatus.OK);
    }
}
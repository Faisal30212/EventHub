package com.eventhub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventhub.entity.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Integer> {

    // Get all gallery images for a particular event
    List<Gallery> findByEventEventId(Integer eventId);

}
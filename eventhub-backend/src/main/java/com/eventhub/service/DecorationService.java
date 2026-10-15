package com.eventhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.DecorationDto;
import com.eventhub.entity.Decoration;
import com.eventhub.repository.DecorationRepository;

@Service
public class DecorationService {

    @Autowired
    private DecorationRepository decorationRepository;

    // Add Decoration
    public String addDecoration(DecorationDto decorationDto) {

        Decoration decoration = new Decoration();

        decoration.setThemeName(decorationDto.getThemeName());
        decoration.setDescription(decorationDto.getDescription());
        decoration.setPrice(decorationDto.getPrice());
        decoration.setImageUrl(decorationDto.getImageUrl());
        decoration.setStatus(decorationDto.getStatus());

        decorationRepository.save(decoration);

        return "Decoration Added Successfully";
    }

    // Get All Decorations
    public List<Decoration> getAllDecorations() {
        return decorationRepository.findAll();
    }

    // Get Decoration By Id
    public Decoration getDecorationById(Integer id) {

        Optional<Decoration> optional = decorationRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    // Update Decoration
    public String updateDecoration(Integer id, DecorationDto decorationDto) {

        Optional<Decoration> optional = decorationRepository.findById(id);

        if (optional.isPresent()) {

            Decoration decoration = optional.get();

            decoration.setThemeName(decorationDto.getThemeName());
            decoration.setDescription(decorationDto.getDescription());
            decoration.setPrice(decorationDto.getPrice());
            decoration.setImageUrl(decorationDto.getImageUrl());
            decoration.setStatus(decorationDto.getStatus());

            decorationRepository.save(decoration);

            return "Decoration Updated Successfully";
        }

        return "Decoration Not Found";
    }

    // Delete Decoration
    public String deleteDecoration(Integer id) {

        if (decorationRepository.existsById(id)) {

            decorationRepository.deleteById(id);

            return "Decoration Deleted Successfully";
        }

        return "Decoration Not Found";
    }
}
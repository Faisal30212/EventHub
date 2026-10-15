package com.eventhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhub.dto.DecorationDto;
import com.eventhub.entity.Decoration;
import com.eventhub.service.DecorationService;

@RestController
@RequestMapping("/decorations")
@CrossOrigin("*")
public class DecorationController {

    @Autowired
    private DecorationService decorationService;

    // Add Decoration
    @PostMapping("/add")
    public ResponseEntity<String> addDecoration(@RequestBody DecorationDto decorationDto) {

        return new ResponseEntity<>(decorationService.addDecoration(decorationDto), HttpStatus.CREATED);
    }

    // Get All Decorations
    @GetMapping
    public List<Decoration> getAllDecorations() {

        return decorationService.getAllDecorations();
    }

    // Get Decoration By Id
    @GetMapping("/{id}")
    public Decoration getDecorationById(@PathVariable Integer id) {

        return decorationService.getDecorationById(id);
    }

    // Update Decoration
    @PutMapping("/{id}")
    public ResponseEntity<String> updateDecoration(@PathVariable Integer id,
        @RequestBody DecorationDto decorationDto) {

        return new ResponseEntity<>(decorationService.updateDecoration(id, decorationDto), HttpStatus.OK);
    }

    // Delete Decoration
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDecoration(@PathVariable Integer id) {

        return new ResponseEntity<>(decorationService.deleteDecoration(id), HttpStatus.OK);
    }
}
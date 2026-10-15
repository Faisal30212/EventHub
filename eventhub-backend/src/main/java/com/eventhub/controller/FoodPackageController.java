package com.eventhub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventhub.dto.FoodPackageDto;
import com.eventhub.entity.FoodPackage;
import com.eventhub.service.FoodPackageService;

@RestController
@RequestMapping("/foodpackages")
@CrossOrigin("*")
public class FoodPackageController {

    @Autowired
    private FoodPackageService foodPackageService;

    // Add Food Package
    @PostMapping("/add")
    public ResponseEntity<String> addFoodPackage(@RequestBody FoodPackageDto foodPackageDto) {

        return new ResponseEntity<>(foodPackageService.addFoodPackage(foodPackageDto), HttpStatus.CREATED);
    }

    // Get All Food Packages
    @GetMapping
    public List<FoodPackage> getAllFoodPackages() {

        return foodPackageService.getAllFoodPackages();
    }

    // Get Food Package By Id
    @GetMapping("/{id}")
    public FoodPackage getFoodPackageById(@PathVariable Integer id) {

        return foodPackageService.getFoodPackageById(id);
    }

    // Update Food Package
    @PutMapping("/{id}")
    public ResponseEntity<String> updateFoodPackage(@PathVariable Integer id,
            @RequestBody FoodPackageDto foodPackageDto) {

        return new ResponseEntity<>(foodPackageService.updateFoodPackage(id, foodPackageDto), HttpStatus.OK);
    }

    // Delete Food Package
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodPackage(@PathVariable Integer id) {

        return new ResponseEntity<>(foodPackageService.deleteFoodPackage(id), HttpStatus.OK);
    }
}
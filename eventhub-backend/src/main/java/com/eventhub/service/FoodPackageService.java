package com.eventhub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventhub.dto.FoodPackageDto;
import com.eventhub.entity.FoodPackage;
import com.eventhub.repository.FoodPackageRepository;

@Service
public class FoodPackageService {

    @Autowired
    private FoodPackageRepository foodPackageRepository;

    // Add Food Package
    public String addFoodPackage(FoodPackageDto foodPackageDto) {

        FoodPackage foodPackage = new FoodPackage();

        foodPackage.setPackageName(foodPackageDto.getPackageName());
        foodPackage.setFoodType(foodPackageDto.getFoodType());
        foodPackage.setPricePerPlate(foodPackageDto.getPricePerPlate());
        foodPackage.setDescription(foodPackageDto.getDescription());
        foodPackage.setStatus(foodPackageDto.getStatus());

        foodPackageRepository.save(foodPackage);

        return "Food Package Added Successfully";
    }

    // Get All Food Packages
    public List<FoodPackage> getAllFoodPackages() {
        return foodPackageRepository.findAll();
    }

    // Get Food Package By Id
    public FoodPackage getFoodPackageById(Integer id) {

        Optional<FoodPackage> optional = foodPackageRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        return null;
    }

    // Update Food Package
    public String updateFoodPackage(Integer id, FoodPackageDto foodPackageDto) {

        Optional<FoodPackage> optional = foodPackageRepository.findById(id);

        if (optional.isPresent()) {

            FoodPackage foodPackage = optional.get();

            foodPackage.setPackageName(foodPackageDto.getPackageName());
            foodPackage.setFoodType(foodPackageDto.getFoodType());
            foodPackage.setPricePerPlate(foodPackageDto.getPricePerPlate());
            foodPackage.setDescription(foodPackageDto.getDescription());
            foodPackage.setStatus(foodPackageDto.getStatus());

            foodPackageRepository.save(foodPackage);

            return "Food Package Updated Successfully";
        }

        return "Food Package Not Found";
    }

    // Delete Food Package
    public String deleteFoodPackage(Integer id) {

        if (foodPackageRepository.existsById(id)) {

            foodPackageRepository.deleteById(id);

            return "Food Package Deleted Successfully";
        }

        return "Food Package Not Found";
    }
}
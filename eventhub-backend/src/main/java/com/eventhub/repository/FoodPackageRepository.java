package com.eventhub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eventhub.entity.FoodPackage;

public interface FoodPackageRepository extends JpaRepository<FoodPackage, Integer> {

}
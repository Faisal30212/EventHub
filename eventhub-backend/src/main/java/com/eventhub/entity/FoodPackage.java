package com.eventhub.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "food_packages")
public class FoodPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodPackageId;

    private String packageName;

    private String foodType;

    private Double pricePerPlate;

    private String description;

    private String status;

    public FoodPackage() {
    }

    public Integer getFoodPackageId() {
        return foodPackageId;
    }

    public void setFoodPackageId(Integer foodPackageId) {
        this.foodPackageId = foodPackageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Double getPricePerPlate() {
        return pricePerPlate;
    }

    public void setPricePerPlate(Double pricePerPlate) {
        this.pricePerPlate = pricePerPlate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
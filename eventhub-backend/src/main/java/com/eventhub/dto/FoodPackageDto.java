package com.eventhub.dto;

public class FoodPackageDto {

    private String packageName;
    private String foodType;
    private Double pricePerPlate;
    private String description;
    private String status;

    public FoodPackageDto() {
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
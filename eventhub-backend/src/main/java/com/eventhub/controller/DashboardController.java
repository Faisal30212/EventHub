package com.eventhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventhub.dto.DashboardDto;
import com.eventhub.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // Get Dashboard Details
    @GetMapping
    public DashboardDto getDashboardDetails() {

        return dashboardService.getDashboardDetails();
    }

}